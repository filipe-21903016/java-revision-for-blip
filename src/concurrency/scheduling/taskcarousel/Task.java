package concurrency.scheduling.taskcarousel;

public class Task implements RRTask {
    private static int counter = 0;  // to give each task a unique ID
    private final int id;
    private int steps;


    public Task(int steps) {
        assert steps >= 0;
        this.steps = steps;
        id = ++counter;
    }

    public void runSlice() {
        steps--;
        System.out.println("Task " + id + " running, steps left: " + steps);
    }

    public boolean isFinished(){
        return steps <= 0;
    }

    public int getId() {
        return id;
    }
}
