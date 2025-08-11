package concurrency.multithreaddownloader.metadata;

public class DownloadMetadata {
    Long fileSizeBytes;
    boolean supportsRange;


    public DownloadMetadata(Long fileSizeBytes, boolean supportsRange) {
        this.fileSizeBytes = fileSizeBytes;
        this.supportsRange = supportsRange;
    }

    public Long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public boolean isSupportsRange() {
        return supportsRange;
    }
}
