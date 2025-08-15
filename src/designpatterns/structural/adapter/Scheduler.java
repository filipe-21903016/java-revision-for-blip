package designpatterns.structural.adapter;

public class Scheduler {
    public void schedule(SchedulableTask task) {
        System.out.println("Scheduling task...");
        task.execute();
    }
}
