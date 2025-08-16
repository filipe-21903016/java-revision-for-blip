package designpatterns.structural.decorator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimingTaskDecorator extends TaskDecorator {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TimingTaskDecorator(Task task) {
        super(task);
    }

    @Override
    public void execute() {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("[LOG] (" + start.format(formatter) + ") Starting task execution");

        this.task.execute();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("[LOG] (" + end.format(formatter) + ") Finished task execution (took " + Duration.between(end, start).getNano() / 1_000_000 + "ms)");
    }
}
