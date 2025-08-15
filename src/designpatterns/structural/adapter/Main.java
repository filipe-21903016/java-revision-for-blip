package designpatterns.structural.adapter;

public class Main {
    public static void main(String[] args) {

        Scheduler scheduler = new Scheduler();
        SchedulableTask task = new LegacyTaskAdapter(new LegacyTask("Legacy task"));
        scheduler.schedule(task);
    }
}
