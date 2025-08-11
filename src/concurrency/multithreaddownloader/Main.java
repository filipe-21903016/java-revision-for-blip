package concurrency.multithreaddownloader;

import concurrency.multithreaddownloader.downloader.IChunkLoaderImpl;
import concurrency.multithreaddownloader.exceptions.DownloadException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("Usage: java ChunkLoaderImpl <file_url> <destination_path> [<number_of_threads>]");
            return;
        }

        String fileUrl = args[0];
        String destinationPath = args[1];
        final int numberOfThreads; // default

        if (args.length >= 3) {
            numberOfThreads = Integer.parseInt(args[2]);
        } else {
            numberOfThreads = 3;
        }

        IChunkLoaderImpl cLoader = new IChunkLoaderImpl();
        Thread downloadThread = new Thread(() -> {
            try {
                cLoader.download(fileUrl, destinationPath, numberOfThreads);
            } catch (DownloadException e) {
                System.out.println("Download failed: " + e.getMessage());
            }
        });
        downloadThread.start();

        while (cLoader.isDownloading()) {
            System.out.printf("Progress: %.2f%%%n", cLoader.getProgress());
            Thread.sleep(1000);
        }

        System.out.println("Download complete!");
    }
}
