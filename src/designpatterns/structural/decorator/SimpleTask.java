package designpatterns.structural.decorator;

public class SimpleTask implements Task {

    @Override
    public void execute() {
        System.out.println("Executing SimpleTask");
    }
}
