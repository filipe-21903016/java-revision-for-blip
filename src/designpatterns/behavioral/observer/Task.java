package designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Task implements Runnable{
    private final List<TaskListener> listeners = new ArrayList<>();
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        notifyAllTaskStarted(taskName);

        // Simulate task progress
        for (int i = 1; i <= 100; i += 10) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAllTaskProgress(taskName, i);
        }

        notifyAllTaskComplete(taskName);
    }


    public void addListener(TaskListener listener) {
        listeners.add(listener);
    }

    public void notifyAllTaskStarted(String taskName) {
        for (TaskListener listener : listeners) {
            listener.onTaskStarted(taskName);
        }
    }

    public void notifyAllTaskProgress(String taskName, int percent) {
        for (TaskListener listener : listeners) {
            listener.onTaskProgress(taskName, percent);
        }
    }

    public void notifyAllTaskComplete(String taskName) {
        for (TaskListener listener : listeners) {
            listener.onTaskComplete(taskName);
        }
    }
}
