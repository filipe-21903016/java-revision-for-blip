package designpatterns.creational;

//Singleton
public class Vault {
    private static volatile Vault instance;
    private String secret;

    private Vault() {}

    public static Vault getInstance() {
        if (instance == null) { // Fast path - Check if resource is initialized without locking
            synchronized (Vault.class) {
                if (instance == null) {
                    instance = new Vault();
                }

            }
        }
        return instance;
    }

    public void storeSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
}
