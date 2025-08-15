package designpatterns.creational.factory;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class SumTask implements Callable<Integer> {
    private final int n;

    public SumTask(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        return IntStream.range(1, n).sum();
    }
}
