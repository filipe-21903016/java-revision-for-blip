package designpatterns.structural.adapter;

public class LegacyTaskAdapter implements SchedulableTask {
    LegacyTask legacyTask;

    public LegacyTaskAdapter(LegacyTask legacyTask) {
        this.legacyTask = legacyTask;
    }

    @Override
    public void execute() {
        legacyTask.runTask();
    }
}
