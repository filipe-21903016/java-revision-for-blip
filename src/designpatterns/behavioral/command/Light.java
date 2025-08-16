package designpatterns.behavioral.command;

public class Light {
    private boolean isOn = false;

    void on() {
        isOn = true;
        System.out.println("The light is ON");
    }

    void off() {
        isOn = false;
        System.out.println("The light is OFF");
    }
}
