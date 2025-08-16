package designpatterns.behavioral.strategy;

import java.util.Arrays;
import java.util.List;

public class StrategyTest {
    public static void main(String[] args) {
        List<Task> tasks = Arrays.asList(
                new Task(3),
                new Task(1),
                new Task(2)
        );

        Scheduler scheduler = new Scheduler(new RoundRobinStrategy());

        scheduler.execute(tasks);

        scheduler.setStrategy(new PriorityScheduling());

        scheduler.execute(tasks);
    }
}
