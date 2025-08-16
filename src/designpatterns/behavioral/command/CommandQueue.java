package designpatterns.behavioral.command;

import java.util.LinkedList;
import java.util.Queue;

public class CommandQueue {
    private Queue<Command> queue = new LinkedList<>();

    public void add(Command command) {
        queue.add(command);
    }

    public void executeAll() {
        while (!queue.isEmpty()) {
            Command command = queue.poll();
            command.execute();
        }
    }
}
