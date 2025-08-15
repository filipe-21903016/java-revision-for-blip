package concurrency.scheduling.taskcarousel;

public class RoundRobinScheduler {
    // CircularQueue should implement a queue interface
    private final CircularQueue<RRTask> circularQueue;
    private volatile boolean paused = false;

    public RoundRobinScheduler(CircularQueue<RRTask> circularQueue) {
        this.circularQueue = circularQueue;
    }

    void start() {
        while(!Thread.currentThread().isInterrupted()){
            synchronized (this){
                while(paused){
                    try{
                        wait();
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            RRTask task = circularQueue.dequeue();
            if (task == null){
                Thread.yield();
                continue;
            }

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

    public void pause(){
        paused = true;
        System.out.println("Scheduler paused");
    }

    public void resume(){
        paused = false;
        synchronized (this){
            notifyAll();
        }
        System.out.println("Scheduler resumed");
    }
}
