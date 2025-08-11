package concurrency.futurewatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static class User {
        String username;
        String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }


    public static void main(String[] args) {
        List<User> users = List.of(
                new User("admin", "admin@email.com"),
                new User("filipe", "filipe@email.com"), // should fail, user already exists
                new User("ricardo", "ricardo@email.com"),
                new User("maria", "maria") // should fail email validation
        );


        IAsyncUserRegistration registration = new AsyncUserRegistrationImpl();

        List<CompletableFuture<Void>> futures = users.stream().map(
                user -> registration.registerUser(user.username, user.email).exceptionally(ex -> {
                    System.err.printf("Error registering user: %s%n: %s%n", user.username, ex.getMessage());
                    return null;
                })
        ).toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> System.out.println("All user registrations completed"))
                .join();


        String username = "ricardo";
        String email = "ricardo@example.com";

        registration.registerUser(username, email)
                .thenRun(() -> System.out.println("Registration completed successfully!"))
                .exceptionally(ex -> {
                    System.err.println("Registration failed: " + ex.getMessage());
                    return null;
                })
                .join();
    }
}
