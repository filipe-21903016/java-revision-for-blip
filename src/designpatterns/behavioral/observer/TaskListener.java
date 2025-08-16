package designpatterns.behavioral.observer;

public interface TaskListener {
    void onTaskStarted(String taskName);
    void onTaskProgress(String taskName, int percent);
    void onTaskComplete(String taskName);
}
