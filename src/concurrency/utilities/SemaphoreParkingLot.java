package concurrency.utilities;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SemaphoreParkingLot {
    private static int randomDelay(int min, int max){
        return (int)((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) throws InterruptedException {
        final int NUM_PARKING_SLOTS = 4;
        final int NUM_CARS = 20;
        final AtomicLong totalTimeWaited = new AtomicLong(0);

        final Semaphore semaphore = new Semaphore(NUM_PARKING_SLOTS);

        Thread[] cars = new Thread[NUM_CARS];

        // normal cars
        for (int i = 0; i < NUM_CARS; i++) {
            final int id = i;
            cars[i] = new Thread(()->{
                System.out.printf("Car:%d arrived to the parking lot", id);
                // Try to park
                try{
                    long startWait = System.currentTimeMillis();
                    semaphore.acquire();
                    long waitedTime = System.currentTimeMillis() - startWait;

                    totalTimeWaited.addAndGet(waitedTime);

                    int parkingTime = randomDelay(1, 5);
                    System.out.printf("Car:%d acquired ticket to the parking lot. Spaces left:%d\n", id, semaphore.availablePermits());
                    Thread.sleep(parkingTime * 1000L);
                    System.out.printf("Car:%d leaving the parking lot (stayed for %d hours). Spaces left:%d\n", id, parkingTime, semaphore.availablePermits());


                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                finally {
                    semaphore.release();
                }
            });

            cars[i].start();
        }

        // VIP car
        Thread vipCar = new Thread(() -> {
            final int id = 999;
            while (true) {
                if (semaphore.tryAcquire()) { // parks only if space is free
                    System.out.printf("VIP Car %d parked! Spaces left: %d\n",
                            id, semaphore.availablePermits());
                    try {
                        int parkingTime = randomDelay(1, 5);
                        Thread.sleep(parkingTime * 1000L);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        System.out.printf("VIP Car %d leaving the parking lot. Spaces left: %d\n",
                                id, semaphore.availablePermits());
                        semaphore.release();
                    }
                    break;
                } else {
                    System.out.println("VIP Car found no space, retrying shortly...");
                    try {
                        Thread.sleep(randomDelay(1, 3) * 1000L);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        vipCar.start();

        for (int i = 0; i < NUM_CARS; i++) {
            cars[i].join();
        }
        vipCar.join();

        System.out.printf("Total waited time %d seconds\n", totalTimeWaited.get() / 1000L);

    }
}
