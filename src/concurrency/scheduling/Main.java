package concurrency.scheduling;

public class Main {
    public static void main(String[] args) {
        CircularQueue<RRTask> queue = new CircularQueue<>(5);
        RoundRobinScheduler scheduler = new RoundRobinScheduler(queue);

        scheduler.submit(new Task(10));
        scheduler.submit(new Task(20));
        scheduler.submit(new Task(2));

        Thread schedulerThread = new Thread(scheduler::start);
        schedulerThread.start();

        // Run scheduler for 10s
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Interrupting scheduler thread...");
        schedulerThread.interrupt();
    }
}
