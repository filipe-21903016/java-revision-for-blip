package designpatterns.structural.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        DatabaseProxy database = new DatabaseProxy();
        database.query("select * from user");
    }
}
