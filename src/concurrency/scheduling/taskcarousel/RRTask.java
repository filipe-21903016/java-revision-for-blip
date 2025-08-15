package concurrency.scheduling.taskcarousel;

public interface RRTask {
    boolean isFinished();
    void runSlice();
    int getId();
}
