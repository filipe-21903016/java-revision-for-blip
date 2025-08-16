package designpatterns.behavioral.strategy;

import java.util.List;

public class RoundRobinStrategy implements SchedulingStrategy {
    @Override
    public void schedule(List<Task> tasks) {
        System.out.println("Scheduling tasks using Round Robin...");
        tasks.forEach(task -> System.out.println("Executing task " + task.getId()));
    }
}
