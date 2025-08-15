package designpatterns.creational.builder;

import java.time.LocalDateTime;

public class SchedulerTest
{
    public static void main(String[] args) {
        Scheduler emailScheduler = new Scheduler.SchedulerBuilder()
                .setTaskName("Email Reminder")
                .setInterval(60)
                .setStartTime(LocalDateTime.now().plusMinutes(5))
                .setRecurring(true)
                .build();

        Scheduler backupScheduler = new Scheduler.SchedulerBuilder()
                .setTaskName("Database Backup")
                .setInterval(3600)
                .setRepeatCount(3)
                .build();

        emailScheduler.run();
        backupScheduler.run();
    }
}
