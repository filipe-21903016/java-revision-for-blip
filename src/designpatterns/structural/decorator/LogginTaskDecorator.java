package designpatterns.structural.decorator;

public class LogginTaskDecorator extends TaskDecorator {
    public LogginTaskDecorator(Task task) {
        super(task);
    }

    @Override
    public void execute() {
        System.out.println("[LOG] Starting task execution");
        this.task.execute();
        System.out.println("[LOG] Finished task execution");
    }
}
