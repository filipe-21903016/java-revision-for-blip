package designpatterns.behavioral.strategy;

import java.util.Comparator;
import java.util.List;

public class PriorityScheduling implements SchedulingStrategy {

    @Override
    public void schedule(List<Task> tasks) {
        System.out.println("Scheduling tasks using Priority Strategy...");
        tasks
            .stream()
            .sorted(Comparator.comparing(Task::getPriority))
            .forEach(task -> {
                System.out.println("Executing task " + task.getId());
            });
    }
}
