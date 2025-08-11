package concurrency.multithreaddownloader.downloader;

import concurrency.multithreaddownloader.exceptions.DownloadException;
import concurrency.multithreaddownloader.metadata.DownloadMetadata;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class IChunkLoaderImpl implements IChunkLoader {
    private ExecutorService executor;
    private DownloadMetadata metadata;
    private volatile boolean isDownloading = false;
    private volatile boolean isCancelled = false;
    private final AtomicLong totalBytesDownloaded = new AtomicLong(0);

    @Override
    public void download(String fileUrl, String destinationPath, int numberOfThreads) throws DownloadException {
        isDownloading = true;

        metadata = getMetadata(fileUrl);
        if (metadata == null || !metadata.isSupportsRange()) {
            throw new DownloadException("Server does not support range requests or failed to get metadata");
        }

        ChunkRange[] ranges = getChunkRanges(metadata.getFileSizeBytes(), numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ChunkDownloader[] downloaders = new ChunkDownloader[numberOfThreads];

        executor = Executors.newFixedThreadPool(numberOfThreads);

        // Submit chunk download tasks
        for (int i = 0; i < numberOfThreads; i++) {
            ChunkRange range = ranges[i];
            downloaders[i] = new ChunkDownloader(range, fileUrl, latch, totalBytesDownloaded, this::isCancelled);
            executor.submit(downloaders[i]);
        }

        // Await downloads to finish
        try{
            latch.await();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new DownloadException("Download interrupted");
        }

        // Combine chunks
        try(FileOutputStream fos = new FileOutputStream(destinationPath)) {
            for (ChunkDownloader downloader : downloaders) {
                byte[] chunkData = downloader.getFileContent();
                if (chunkData == null) {
                    throw new DownloadException("Chunk data missing for range: " + downloader.chunkRange);
                }
                fos.write(chunkData);
            }
        } catch (IOException e) {
            throw new DownloadException("Failed to write combined file");
        } finally {
            executor.shutdown();
            isDownloading = false;
        }
    }

    private ChunkRange[] getChunkRanges(long fileSizeBytes, int numberOfThreads) {
        ChunkRange[] ranges = new ChunkRange[numberOfThreads];
        long chunkSize = fileSizeBytes / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            long start = i * chunkSize;
            long end = (i == numberOfThreads - 1) ? fileSizeBytes - 1 : (start + chunkSize - 1);
            ranges[i] = new ChunkRange(start, end);
        }
        return ranges;
    }

    @Override
    public double getProgress() {
        if (metadata == null) return 0.0;
        return ((double) totalBytesDownloaded.get() / metadata.getFileSizeBytes()) * 100.0;
    }

    @Override
    public void cancel() {
        isCancelled = true;
        if (executor != null) {
            executor.shutdownNow();
            try{
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)){
                    System.out.println("Some download threads did not terminate in time.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Cancel wait interrupted.");
            }
        }
        isDownloading = false;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public boolean isDownloading() {
        return isDownloading;
    }

    DownloadMetadata getMetadata(String fileUrl) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(fileUrl))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            boolean supportsRange = "bytes".equalsIgnoreCase(response.headers().firstValue("Accept-Ranges").orElse(null));
            Long fileSizeBytes = Long.parseLong(Objects.requireNonNull(response.headers().firstValue("Content-Length").orElse(null)));

            return new DownloadMetadata(fileSizeBytes, supportsRange);
        } catch (Exception e) {
            // TODO: replace with better logging
            e.printStackTrace();
            return null;
        }
    }
}


