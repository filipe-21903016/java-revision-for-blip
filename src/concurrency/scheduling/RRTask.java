package concurrency.scheduling;

public interface RRTask {
    boolean isFinished();
    void runSlice();
    int getId();
}
