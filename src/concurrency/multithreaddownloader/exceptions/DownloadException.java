package concurrency.multithreaddownloader.exceptions;

public class DownloadException extends RuntimeException {
    public DownloadException(String message) {
        super(message);
    }
}
