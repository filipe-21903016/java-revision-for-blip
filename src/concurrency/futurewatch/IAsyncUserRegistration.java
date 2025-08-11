package concurrency.futurewatch;

import java.util.concurrent.CompletableFuture;

public interface IAsyncUserRegistration {

    /**
     * Validate user input asynchronously.
     * Should complete exceptionally if validation fails.
     *
     * @param username the username to validate
     * @param email the user's email
     * @return a CompletableFuture that completes when validation is done
     */
    CompletableFuture<Void> validateUser(String username, String email);

    /**
     * Save user asynchronously.
     *
     * @param username the username to save
     * @param email the user's email
     * @return a CompletableFuture that completes with the generated user ID
     */
    CompletableFuture<Integer> saveUser(String username, String email);

    /**
     * Send welcome email asynchronously.
     *
     * @param userId the ID of the saved user
     * @param email the user's email address
     * @return a CompletableFuture that completes when the email is sent
     */
    CompletableFuture<Void> sendWelcomeEmail(int userId, String email);


    /**
     * Register user async pipeline:
     * 1. Validating input
     * 2. Saving user
     * 3. Sending welcome email
     *
     * @param username the username to register
     * @param email the user's email
     * @return a CompletableFuture that completes when registration is done
     */
    default CompletableFuture<Void> registerUser(String username, String email) {
        return validateUser(username, email)
                .thenCompose(v -> saveUser(username, email))
                .thenCompose(userId -> sendWelcomeEmail(userId, email));
    }

}

