package designpatterns.behavioral.strategy;

import java.util.List;

public interface SchedulingStrategy {
    void schedule(List<Task> tasks);
}
