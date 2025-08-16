package designpatterns.structural.decorator;

public abstract class TaskDecorator implements Task {
    Task task;

    public TaskDecorator(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        this.task.execute();
    }
}
