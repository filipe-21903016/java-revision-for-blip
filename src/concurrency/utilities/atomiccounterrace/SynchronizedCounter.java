package concurrency.utilities.atomiccounterrace;

public class SynchronizedCounter implements ICounter {
    int counter = 0;


    @Override
    synchronized public void increment() {
        counter+=1;
    }

    @Override
    synchronized public int getValue() {
        return counter;
    }
}
