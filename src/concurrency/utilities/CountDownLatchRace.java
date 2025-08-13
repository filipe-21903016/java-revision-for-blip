package concurrency.utilities;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchRace {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_RUNNERS = 4;
        final CountDownLatch latch = new CountDownLatch(1);
        final CountDownLatch finishLineLatch = new CountDownLatch(NUM_RUNNERS);
        Thread[] runners = new Thread[NUM_RUNNERS];

        // Line-up runners
        for (int i = 0; i < NUM_RUNNERS; i++) {
            final int runnerId = i;
            runners[i] = new Thread(()-> {

                try {
                    System.out.printf("Runner %d, ready!\n", runnerId);
                    latch.await();
                    System.out.printf("Runner %d started sprinting!\n", runnerId);
                    Thread.sleep(runnerId * 1000);
                    System.out.println("Runner " + runnerId + " finished");
                    finishLineLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            runners[i].start();
        }

        Thread.sleep(1000); // Prep time
        System.out.println("On your marks, get set, GO!");
        latch.countDown();

        finishLineLatch.await();
        System.out.println("Finished all runners");
    }
}
