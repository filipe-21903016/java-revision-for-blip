package concurrency.multithreaddownloader.downloader;

public class ChunkRange {
    long startByte;
    long endByte;

    public ChunkRange(long startByte, long endByte) {
        this.startByte = startByte;
        this.endByte = endByte;
    }

    public long getStartByte() {
        return startByte;
    }

    public long getEndByte() {
        return endByte;
    }

    @Override
    public String toString() {
        return "ChunkRange{" + startByte + ':' + endByte + '}';
    }
}
