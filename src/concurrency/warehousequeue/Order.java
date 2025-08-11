package concurrency.warehousequeue
        ;

public class Order {
    int orderId;

    public Order() {
        this.orderId = (int) (Math.random() * 10000);
    }

    public int getOrderId() {
        return orderId;
    }
}
