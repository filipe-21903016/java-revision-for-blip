package concurrency.deadlocks;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class LivelockDancers {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean t1WantsResource = new  AtomicBoolean(false);
        AtomicBoolean t2WantsResource = new  AtomicBoolean(false);

        Thread t1 = new Thread(() -> {
            while (true){
                t1WantsResource.set(true);


                // Check if thread2 wants resource
                if (t2WantsResource.get()){
                    System.out.println("T1: T2 wants resource, backing off");
                    sleep(200);
                    t1WantsResource.set(false); // Back off resource
                } else {
                    System.out.println("T1: Acting on resource");
                    sleep(1000); // working
                    t1WantsResource.set(false);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                t2WantsResource.set(true);

                if (t1WantsResource.get()) {
                    System.out.println("T2: T1 wants resource, backing off");
                    sleep(200);
                    t2WantsResource.set(false); // backing off
                } else {
                    System.out.println("T2: Acting on resource");
                    sleep(1000); //work
                    t2WantsResource.set(false);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
