package designpatterns.behavioral.strategy;

import java.util.OptionalInt;

public class Task {
    private static int counter = 0;
    private int id;
    private Integer priority;


    public Task(Integer priority) {
        id=++counter;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public Integer getPriority() {
        return priority;
    }
}
