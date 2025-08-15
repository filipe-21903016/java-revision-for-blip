package designpatterns.structural.adapter;

public class LegacyTask {
    private String taskName;

    public LegacyTask(String taskName) {
        this.taskName = taskName;
    }

    public void runTask() {
        System.out.println("Executing legacy task " + taskName);
    }
}
