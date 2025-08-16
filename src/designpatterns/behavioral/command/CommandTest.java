package designpatterns.behavioral.command;

public class CommandTest {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Light kitchenLight = new Light();

        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command kitchenOn = new LightOnCommand(kitchenLight);

        CommandQueue queue = new CommandQueue();

        queue.add(livingRoomOn);
        queue.add(kitchenOn);

        queue.executeAll();
    }
}
