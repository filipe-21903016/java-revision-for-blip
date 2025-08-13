package concurrency.utilities.atomiccounterrace;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements  ICounter{
    AtomicInteger counter = new AtomicInteger(0);


    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public int getValue() {
        return counter.get();
    }
}
