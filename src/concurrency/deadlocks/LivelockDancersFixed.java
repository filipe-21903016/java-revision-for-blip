package concurrency.deadlocks;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class LivelockDancersFixed {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean t1WantsResource = new  AtomicBoolean(false);
        AtomicBoolean t2WantsResource = new  AtomicBoolean(false);

        Thread t1 = new Thread(() -> performResourceAction(t1WantsResource, t2WantsResource, true, "Thread1"));
        Thread t2 = new Thread(() -> performResourceAction(t2WantsResource, t1WantsResource, false, "Thread2"));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void performResourceAction(
            AtomicBoolean myFlag,
            AtomicBoolean otherFlag,
            boolean iAmPriority,
            String threadName
    ) {
        while (true) {
            myFlag.set(true);

            if (otherFlag.get() && !iAmPriority) {
                System.out.println(threadName + ": Other thread wants resource, backing off");
                sleepRandom(100, 300);
                myFlag.set(false); // Back off
            } else {
                System.out.println(threadName + ": Acting on resource");
                sleep(1000); // Working
                myFlag.set(false);
            }
        }
    }


    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }

    private static void sleepRandom(int minMillis, int maxMillis){
        try {
            Thread.sleep(new Random().nextInt(maxMillis - minMillis + 1) + minMillis);
        } catch (InterruptedException ignored) {}
    }
}
