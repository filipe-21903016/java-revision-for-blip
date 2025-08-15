package concurrency.scheduling.taskcarousel;

public class RoundRobinScheduler {
    // CircularQueue should implement a queue interface
    private final CircularQueue<RRTask> circularQueue;

    public RoundRobinScheduler(CircularQueue<RRTask> circularQueue) {
        this.circularQueue = circularQueue;
    }

    void start() {
        while(!Thread.currentThread().isInterrupted()){
            RRTask task = circularQueue.dequeue();

            task.runSlice();

            if(!task.isFinished()){
                circularQueue.enqueue(task);
                System.out.println("Requeuing task "+ task.getId());
            }else{
                System.out.println("Task " +  task.getId() + " is finished");
            }
        }
    }

    void submit(RRTask task){
        circularQueue.enqueue(task);
    }
}
