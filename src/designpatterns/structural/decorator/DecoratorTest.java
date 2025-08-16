package designpatterns.structural.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Task task = new TimingTaskDecorator(new LogginTaskDecorator(new SimpleTask()));
        task.execute();
    }
}
