package concurrency.scheduling.taskcarousel;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CircularQueue<RRTask> queue = new CircularQueue<>(5);
        RoundRobinScheduler scheduler = new RoundRobinScheduler(queue);

        scheduler.submit(new Task(100));
        scheduler.submit(new Task(50));
        scheduler.submit(new Task(10));

        Thread schedulerThread = new Thread(scheduler::start);
        schedulerThread.start();

        // Run scheduler for 10s
        System.out.println("Main:Scheduler started");
        sleep(1_000);


        scheduler.pause();
        System.out.println("Main:Scheduler paused");
        sleep(10_000);

        scheduler.resume();
        System.out.println("Main:Scheduler resumed");



        sleep(20_000);
        schedulerThread.interrupt();
    }

    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
