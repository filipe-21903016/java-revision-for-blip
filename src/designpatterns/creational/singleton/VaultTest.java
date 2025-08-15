package designpatterns.creational.singleton;

public class VaultTest {
    public static void main(String[] args) {
        Runnable task = () -> {
            Vault vault = Vault.getInstance();
            System.out.println(Thread.currentThread().getName() + " -> Vault instance hash: " + vault.hashCode());

            vault.storeSecret(Thread.currentThread().getName() + "'s secret");
            System.out.println(Thread.currentThread().getName() + " stored secret: " + vault.getSecret());
        };

        // Start 5 threads
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task, "Thread-" + (i + 1));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Final check of Vault instance
        Vault vault = Vault.getInstance();
        System.out.println("Final Vault secret: " + vault.getSecret());
        System.out.println("Final Vault instance hash: " + vault.hashCode());
    }
}
