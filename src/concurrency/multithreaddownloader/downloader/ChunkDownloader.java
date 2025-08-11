package concurrency.multithreaddownloader.downloader;

import concurrency.multithreaddownloader.utils.ICancellationChecker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class ChunkDownloader implements Runnable {
    ChunkRange chunkRange;
    String fileUrl;
    private byte[] fileContent;
    private final CountDownLatch latch;
    // Track chunk progress
    private final AtomicLong chunkBytesDownloaded = new AtomicLong(0);
    // Shared atomic for all chunks
    private final AtomicLong totalBytesDownloaded;
    private final ICancellationChecker ICancellationChecker;

    public ChunkDownloader(ChunkRange chunkRange, String fileUrl, CountDownLatch latch, AtomicLong totalBytesDownloaded, ICancellationChecker ICancellationChecker) {
        this.chunkRange = chunkRange;
        this.fileUrl = fileUrl;
        this.latch = latch;
        this.totalBytesDownloaded = totalBytesDownloaded;
        this.ICancellationChecker = ICancellationChecker;
    }


    @Override
    public void run() {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(fileUrl))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .headers("Range", String.format("bytes=%d-%d", chunkRange.startByte, chunkRange.endByte))
                    .build();
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 206) {
                try (InputStream is = response.body();
                     ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                    byte[] buffer = new byte[8192];
                    int bytesRead;

                    while ((bytesRead = is.read(buffer)) != -1) {
                        if(ICancellationChecker.isCancelled()){
                            System.out.println("Download cancelled for range: " + chunkRange);
                            break;
                        }

                        baos.write(buffer, 0, bytesRead);

                        // Update counters
                        chunkBytesDownloaded.addAndGet(bytesRead);
                        totalBytesDownloaded.addAndGet(bytesRead);
                    }

                    fileContent = baos.toByteArray();
                }
            } else {
                throw new RuntimeException("Unexpected response status: " + response.statusCode());
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                System.out.println("Download interrupted for range: " + chunkRange);
                Thread.currentThread().interrupt();
            } else {
                throw new RuntimeException(e);
            }
        } finally {
            latch.countDown();
        }
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public long getChunkBytesDownloaded() {
        return chunkBytesDownloaded.get();
    }
}
