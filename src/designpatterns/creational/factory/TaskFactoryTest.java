package designpatterns.creational.factory;

import java.util.concurrent.*;

public class TaskFactoryTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Runnable
        Runnable printTask = TaskFactory.createRunnableTask("print");
        Thread printThread = new Thread(printTask);
        printThread.start();

        Runnable delayTask = TaskFactory.createRunnableTask("delay");
        Thread delayThread = new Thread(delayTask);
        delayThread.start();

        //Callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> sumTask = (Callable<Integer>) TaskFactory.createCallableTask("sum");
        Future<Integer> sumFuture = executorService.submit(sumTask);
        System.out.println("Sum result: " + sumFuture.get());

        executorService.shutdown();

        printThread.join();
        delayThread.join();
    }
}
