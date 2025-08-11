package concurrency.warehousequeue.lowlevel;

import concurrency.warehousequeue.Order;

import java.util.concurrent.BlockingQueue;

public class OrderProducer implements Runnable {
    long delay;
    int numberOfOrders;
    OrderQueue queue;
    String producerId;

    public OrderProducer(String producerId, OrderQueue queue, long delay, int numberOfOrders) {
        this.delay = delay;
        this.queue = queue;
        this.numberOfOrders = numberOfOrders;
        this.producerId = producerId;
    }

    private void sleep(long milis) {
        try{
            Thread.sleep(milis);
        } catch (InterruptedException ignored){}
    }

    @Override
    public void run() {

        try {

            for (int i = 0; i < numberOfOrders; i++) {
                sleep(delay);
                var o = new Order();
                queue.put(o); // block if queue is full
                System.out.printf("Producer %s added order{%d}%n", producerId, o.getOrderId());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
