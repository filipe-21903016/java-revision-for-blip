package designpatterns.creational.factory;

import java.util.concurrent.Callable;

public class TaskFactory {
    public static Runnable createRunnableTask(String type){
        return switch (type.toLowerCase()) {
            case "print" -> new PrintTask("Hello from PrintTask");
            case "delay" -> new DelayTask(2000, "Delayed 2s");
            default -> throw new IllegalArgumentException("Unknown Runnable task type");
        };
    }
    public static Callable<?> createCallableTask(String type){
        return switch (type.toLowerCase()) {
            case "sum" -> new SumTask(5);
            default -> throw new IllegalArgumentException("Unknown Runnable task type");
        };
    }
}
