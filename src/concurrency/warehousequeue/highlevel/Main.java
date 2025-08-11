package concurrency.warehousequeue.highlevel;

import concurrency.warehousequeue.Order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>(5);

        int numberOfProducers = 10;
        int numberOfConsumers = 5;

        for (int i = 0; i < numberOfProducers; i++) {
            var producer = new OrderProducer(Integer.toString(i), queue, 500, 3);
            new Thread(producer, "Producer-" + i).start();
        }

        Thread.sleep(1000); // small delay to fill queue

        for (int i = 0; i < numberOfConsumers; i++) {
            var consumer = new OrderConsumer(queue, 1000);
            new Thread(consumer, "Consumer-" + i).start();
        }
    }


}
