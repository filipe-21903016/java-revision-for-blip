package designpatterns.behavioral.observer;

public class ProgressTrackerListener implements TaskListener {

    @Override
    public void onTaskStarted(String taskName) {

    }

    @Override
    public void onTaskProgress(String taskName, int percent) {
        System.out.println("Task progress: " + taskName + " - " + percent + "%");
    }

    @Override
    public void onTaskComplete(String taskName) {
    }
}
