package designpatterns.behavioral.observer;

public class ObserverTest {
    public static void main(String[] args) throws InterruptedException {
        Task task1 = new Task("Task 1");

        TaskListener consoleLogger = new ConsoleLoggerListener();
        TaskListener progressTracker = new ProgressTrackerListener();

        task1.addListener(consoleLogger);
        task1.addListener(progressTracker);

        Thread t =  new Thread(task1);
        t.start();

        t.join();
    }
}
