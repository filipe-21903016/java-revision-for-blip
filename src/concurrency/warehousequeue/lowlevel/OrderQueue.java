package concurrency.warehousequeue.lowlevel;

import concurrency.warehousequeue.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OrderQueue {
    private final List<Order> buffer = new LinkedList<>();
    private final int capacity;

    private final  ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(Order order) throws InterruptedException{
        lock.lock();
        try{
            while (buffer.size() == capacity){
                System.out.println(Thread.currentThread().getName() + " waiting: Buffer full");
                notFull.await(); // wait here if queue is full
            }

            buffer.add(order);
            System.out.println(Thread.currentThread().getName() + " produced: " + order.getOrderId());
            notEmpty.signal(); // Wake up waiting consumer
        } finally {
            lock.unlock();
        }
    }

    public Order poll(long timeout, TimeUnit timeUnit) throws InterruptedException{
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (buffer.isEmpty()){
                if (nanos <= 0L) {
                    return null; // timeout reached
                }
                System.out.println(Thread.currentThread().getName() + " waiting: Buffer empty");
                nanos = notEmpty.awaitNanos(nanos); // wait here if queue is empty
            }

            var order = buffer.remove(0);
            System.out.println(Thread.currentThread().getName() + " consumed: " + order.getOrderId());
            notFull.signal();
            return order;
        }finally {
            lock.unlock();
        }
    }
}
