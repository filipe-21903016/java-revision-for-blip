package designpatterns.behavioral.observer;

public class ConsoleLoggerListener implements TaskListener{

    @Override
    public void onTaskStarted(String taskName) {
        System.out.println("Task " + taskName + " started");
    }

    @Override
    public void onTaskProgress(String taskName, int percent) {
    }

    @Override
    public void onTaskComplete(String taskName) {
        System.out.println("Task " + taskName + " completed");
    }
}
