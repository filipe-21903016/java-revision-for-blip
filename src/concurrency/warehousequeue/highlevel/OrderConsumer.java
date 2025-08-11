package concurrency.warehousequeue.highlevel;

import concurrency.warehousequeue.Order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class OrderConsumer implements Runnable {
    BlockingQueue<Order> queue;
    int delay;
    int timeout;

    public OrderConsumer(BlockingQueue<Order> queue, int delay) {
        this(queue, delay, 30000);
    }

    public OrderConsumer(BlockingQueue<Order> queue, int delay,  int timeout) {
        this.queue = queue;
        this.delay = delay;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try{
            while(true){
                Order order = queue.poll(timeout, TimeUnit.MICROSECONDS);
                if (order == null) {
                    System.out.println(Thread.currentThread().getName() + " timed out waiting for orders.");
                    break; // exit loop if no order within timeout
                }
                System.out.println(Thread.currentThread().getName() + " received order: " + order.getOrderId());
                sleep(delay);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sleep(long milis) {
        try{
            Thread.sleep(milis);
        } catch (InterruptedException ignored){}
    }



}
