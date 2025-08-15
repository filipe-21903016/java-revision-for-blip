package designpatterns.creational.factory;


public class PrintTask implements Runnable {
    private final String message;
    public PrintTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message);
    }
}
