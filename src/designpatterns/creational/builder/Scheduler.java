package designpatterns.creational.builder;

import java.time.LocalDateTime;

public class Scheduler {
    private final int interval;
    private final LocalDateTime startTime;
    private final int repeatCount;
    private final String  taskName;
    private final boolean isRecurring;



    public Scheduler(int interval, LocalDateTime startTime, int repeatCount, String taskName, boolean isRecurring) {
        this.interval = interval;
        this.startTime = startTime;
        this.repeatCount = repeatCount;
        this.taskName = taskName;
        this.isRecurring = isRecurring;
    }

    public Scheduler(SchedulerBuilder builder) {
        this.interval = builder.interval;
        this.startTime = builder.startTime;
        this.repeatCount = builder.repeatCount;
        this.taskName = builder.taskName;
        this.isRecurring = builder.isRecurring;
    }

    public void run() {
        System.out.println("Running " + taskName + " every " + interval + "s");
    }

    public static class SchedulerBuilder {
        private int interval = 60;
        private LocalDateTime startTime = LocalDateTime.now();
        private int repeatCount = 1;
        private String taskName;
        private boolean isRecurring = false;
        private boolean built = false;

        private void checkIfBuilt(){
            if (built) {
                throw new IllegalStateException("Builder cannot be reused after build()");
            }
        }

        private void validate(){
            // Validation
            if (interval <= 0) {
                throw new IllegalArgumentException("Interval must be greater than 0");
            }
            if (repeatCount < 0) {
                throw new IllegalArgumentException("Repeat count cannot be negative");
            }
            if (taskName == null || taskName.isEmpty()) {
                throw new IllegalArgumentException("Task name must be provided");
            }
        }

        public SchedulerBuilder setInterval(int interval) {
            checkIfBuilt();
            this.interval = interval;
            return this;
        }

        public SchedulerBuilder setStartTime(LocalDateTime startTime) {
            checkIfBuilt();
            this.startTime = startTime;
            return this;
        }

        public SchedulerBuilder setRepeatCount(int repeatCount) {
            checkIfBuilt();
            this.repeatCount = repeatCount;
            return this;
        }

        public SchedulerBuilder setTaskName(String taskName) {
            checkIfBuilt();
            this.taskName = taskName;
            return this;
        }

        public SchedulerBuilder setRecurring(boolean recurring) {
            checkIfBuilt();
            this.isRecurring = recurring;
            return this;
        }

        public Scheduler build() {
            checkIfBuilt();
            validate();

            built = true;
            return new Scheduler(this);
        }
    }
}
