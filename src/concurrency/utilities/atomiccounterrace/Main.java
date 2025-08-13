package concurrency.utilities.atomiccounterrace;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_THREADS = 4;
        final int NUM_INCREMENTS = 25_000_000;

        stressTest(new AtomicCounter(), "AtomicInteger Counter", NUM_THREADS, NUM_INCREMENTS);
        stressTest(new SynchronizedCounter(), "Synchronized Counter", NUM_THREADS, NUM_INCREMENTS);
    }


    private static void stressTest(ICounter counter, String name, int num_threads, int num_increments) throws InterruptedException {
        Thread[] threads = new Thread[num_threads];

        long startTime = System.nanoTime();

        // Start threads
        for(int i = 0; i < num_threads; i++){
            threads[i] = new Thread(() ->{
                for (int j =0 ;j<num_increments; j++){
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // Wait threads to join
        for (Thread t: threads){
            t.join();
        }

        long endTime = System.nanoTime();

        System.out.printf("%s -> Final count: %d, Time: %.2f ms%n",
                name,
                counter.getValue(),
                (endTime - startTime) / 1_000_000.0
        );
    }
}
