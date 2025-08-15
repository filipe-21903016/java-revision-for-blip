package concurrency.scheduling;

import concurrency.scheduling.taskcarousel.Task;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeSpinner {

    public static void main(String[] args) {
        int numOfTasks = 5;
        ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();

        // Add tasks to queue
        for(int i = 0; i < numOfTasks; i++) {
            tasks.add(new Task(i + 5));
        }

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            execute(tasks);
        }, 0, 1, TimeUnit.SECONDS);


        try{
            // Add small delay so executor starts before we add new tasks to the queue
            Thread.sleep(5_000);
            for(int i = 0; i < numOfTasks; i++) {
                var t = new Task(i + 1);
                tasks.add(t);
                System.out.println("Added new task: " + t.getId());
            }

            // Shutdown executor after 60s
            Thread.sleep(60_000);
            scheduledExecutorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void execute(ConcurrentLinkedQueue<Task> tasks) {
        Task task = tasks.poll();
        if (task == null) return;
        task.runSlice();
        if (!task.isFinished()) {
            tasks.add(task);
        }
    }
}
