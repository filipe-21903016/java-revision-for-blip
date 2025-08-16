package designpatterns.behavioral.command;

public class Fan {
    private boolean isOn = false;

    void start() {
        isOn = true;
    }

    void stop() {
        isOn = false;
    }
}
