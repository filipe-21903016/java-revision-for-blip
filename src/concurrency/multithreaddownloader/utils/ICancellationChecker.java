package concurrency.multithreaddownloader.utils;

@FunctionalInterface
public interface ICancellationChecker {
    boolean isCancelled();
}
