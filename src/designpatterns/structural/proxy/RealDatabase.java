package designpatterns.structural.proxy;

public class RealDatabase implements Database {
    public RealDatabase() {
        // Simulate expensive connection
        try{
         Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void query(String sql) {
        System.out.println("[QUERY]: " + sql);
    }
}
