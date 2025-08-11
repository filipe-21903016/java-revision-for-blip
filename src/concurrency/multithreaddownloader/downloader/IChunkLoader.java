package concurrency.multithreaddownloader.downloader;

import concurrency.multithreaddownloader.exceptions.DownloadException;

public interface IChunkLoader {

    /**
     * Downloads file from the given URL using multiple threads.
     *
     * @param fileURL The URL of the file to download.
     * @param destinationPath The local file path where the downloaded file will be saved.
     * @param numberOfThreads Number of concurrent threads to use for downloading chunks.
     * @throws DownloadException If an error occurs during the download process.
     */
    void download(String fileUrl, String destinationPath, int numberOfThreads) throws DownloadException;

    /**
     * Returns the progress of the download in percentage
     *
     * @return download progress in percentage
     */
    double getProgress();

    /**
     * Cancels ongoing download (if any)
     */
    void cancel();

    /**
     * Check if there's a download in progress
     *
     * @return true if downloading, false otherwise
     */
    boolean isDownloading();
}
