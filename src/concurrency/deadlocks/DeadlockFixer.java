package concurrency.deadlocks;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockFixer {
    public static void main(String[] args) {
        ReentrantLock resourceA = new ReentrantLock();
        ReentrantLock resourceB = new ReentrantLock();

        int timeout = 10;
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("T1: Trying to lock Resource A");
                if (resourceA.tryLock(timeout, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("T1: Locked Resource A");

                        Thread.sleep(3000);

                        System.out.println("T1: Trying to lock Resource B");
                        if (resourceB.tryLock(timeout, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("T1: Locked Resource B");
                                // do something here with both locks held
                            } finally {
                                resourceB.unlock();
                                System.out.println("T1: Released Resource B");
                            }
                        } else {
                            System.out.println("T1: Could not lock Resource B within timeout");
                        }

                    } finally {
                        resourceA.unlock();
                        System.out.println("T1: Released Resource A");
                    }
                } else {
                    System.out.println("T1: Could not lock Resource A within timeout");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("T1: Interrupted");
            }
        });



        Thread t2 = new Thread(() -> {
            try {
                System.out.println("T2: Trying to lock Resource B");
                if (resourceB.tryLock(timeout, TimeUnit.SECONDS)){
                    try {
                        System.out.println("T2: Locked Resource B");
                        Thread.sleep(2000);
                        System.out.println("T2: Trying to lock Resource A");
                        if(resourceA.tryLock(timeout, TimeUnit.SECONDS)){
                            try{
                                System.out.println("T2: Locked Resource A");
                                // do something here with both locks held
                            }finally {
                                resourceA.unlock();
                                System.out.println("T2: Released Resource A");
                            }
                        }else{
                            System.out.println("T2: Could not lock Resource A within timeout");
                        }
                    }finally {
                        resourceB.unlock();
                        System.out.println("T2: Released Resource B");
                    }
                }else{
                    System.out.println("T2: Could not lock Resource B within timeout");
                }
            }catch (InterruptedException ignored) {
            }
        });

        t1.start();
        t2.start();
    }
}
