package concurrency.utilities;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierConstruction {
    public static void main(String[] args) {
        final int NUM_WORKERS = 3;
        CyclicBarrier barrier = new CyclicBarrier(NUM_WORKERS);


        Thread[] workers = new Thread[NUM_WORKERS];

        for (int i = 0; i < NUM_WORKERS; i++) {
            final int workerId = i;
            workers[i] = new Thread(()->{
                try {
                    work(workerId, barrier);
                } catch (BrokenBarrierException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            workers[i].start();
        }

        for (int i = 0; i < NUM_WORKERS; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException ignored) {}
        }

        System.out.println("House construction finished");


    }

    private static void work(int workerId, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        String[] stages = {"foundation", "walls", "roof", "paint"};

        for(String stage: stages) {
            try {
                System.out.printf("Worker %d started %s\n", workerId, stage);
                Thread.sleep((int) ((Math.random() * (10_000 - 1_000)) + 1_000));
                System.out.printf("Worker %d finished %s\n", workerId, stage);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Work interrupted for worker " + workerId);
            }

            barrier.await();
        }
    }
}
