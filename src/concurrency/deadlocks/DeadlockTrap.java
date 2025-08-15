package concurrency.deadlocks;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockTrap {
    public static void main(String[] args) {
        ReentrantLock resourceA = new ReentrantLock();
        ReentrantLock resourceB = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            System.out.println("T1: Trying to lock Resource A");
            resourceA.lock();
            System.out.println("T1: Locked Resource A");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("T1: Trying to lock Resource B");
            resourceB.lock();
            System.out.println("T1: Locked Resource B");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("T2: Trying to lock Resource B");
            resourceB.lock();
            System.out.println("T2: Locked Resource B");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("T2: Trying to lock Resource A");
            resourceA.lock();
            System.out.println("T2: Locked Resource A");
        });

        t1.start();
        t2.start();
    }
}
