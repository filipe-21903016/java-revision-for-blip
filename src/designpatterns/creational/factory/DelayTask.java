package designpatterns.creational.factory;

public class DelayTask implements Runnable {
    private final int delayMillis;
    private final String message;

    public DelayTask(int delayMillis, String message) {
        this.delayMillis = delayMillis;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delayMillis);
            System.out.println(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
