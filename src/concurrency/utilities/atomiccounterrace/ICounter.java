package concurrency.utilities.atomiccounterrace;

public interface ICounter {
    public void increment();
    int getValue();
}
