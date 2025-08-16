package designpatterns.creational.prototype;

import java.time.LocalDateTime;
import java.util.List;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Task dailyStandup = new Task("Daily Standup", "Sync with the team", 1, 0, LocalDateTime.now(), List.of("meeting"));
        Task myDaily = dailyStandup.clone();

        myDaily.setAssignedToUserId(42);
        myDaily.setDueDate(LocalDateTime.now().plusHours(1));
        myDaily.getTags().add("urgent");

        System.out.println("Original Task Tags: " + dailyStandup.getTags());
        System.out.println("Cloned Task Tags:   " + myDaily.getTags());
    }
}
