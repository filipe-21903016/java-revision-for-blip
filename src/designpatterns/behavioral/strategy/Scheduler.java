package designpatterns.behavioral.strategy;

import java.util.List;

public class Scheduler {
    private SchedulingStrategy strategy;

    public Scheduler(SchedulingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SchedulingStrategy strategy) {
        this.strategy = strategy;
    }

    void execute(List<Task> tasks) {
        this.strategy.schedule(tasks);
    }
}
