package designpatterns.structural.proxy;


public class DatabaseProxy implements Database {
    RealDatabase realDatabase;

    public DatabaseProxy() {}

    @Override
    public void query(String sql) {
        if (realDatabase == null ){
            realDatabase = new RealDatabase();
        }
        System.out.println("(DatabaseProxy) Executing query: " + sql);
        this.realDatabase.query(sql);
    }
}
