package concurrency.futurewatch;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

public class AsyncUserRegistrationImpl implements IAsyncUserRegistration {
    ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>(Map.of("filipe", "filipe@email.com"));

    @Override
    public CompletableFuture<Void> validateUser(String username, String email) {
        return CompletableFuture.runAsync(() -> {
            sleep(1000);
            if (users.containsKey(username)) {
                throw new RuntimeException("Username already exists");
            }
            // Dummy email check
            if (!email.contains("@")){
                throw new RuntimeException("Invalid email");
            }
        });
    }

    @Override
    public CompletableFuture<Integer> saveUser(String username, String email) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            int userId = (int) (Math.random() * 10000);
            System.out.println("User saved with ID: " + userId);
            return userId;
        });
    }

    @Override
    public CompletableFuture<Void> sendWelcomeEmail(int userId, String email) {
        return CompletableFuture.runAsync(() -> {
            sleep(500);
            System.out.println("Welcome email sent to " + email + " for user ID: " + userId);
        });
    }

    private static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException ignored) {}
    }
}
