### **Phase 1 – Locks & Concurrency Fundamentals**

**1.1 – Core Threading Concepts**

- [x]  🗂 **Chunk Loader** – Multi-threaded file downloader where each thread downloads a separate chunk, and the chunks are combined into a single file.
- [x]  ⚡ **Parallel Fetcher** – Implement the same task using `Runnable` and `Callable` via `ExecutorService`, and compare how to retrieve results.
- [x]  ⏳ **Future Watch** – Practice chaining asynchronous tasks with `Future` and `CompletableFuture` to process data in steps.

**1.2 – Locks & Synchronization**

- [x]  🛒 **Warehouse Queue** – Use a `BlockingQueue` to build a producer-consumer system simulating order processing.
- [x]  🔒 **Lock & Signal** – Recreate the producer-consumer model using explicit `ReentrantLock` and `Condition`variables for thread signaling.
- [ ]  🔄 **Chunk Writer** – Implement synchronized writing to a shared file by multiple threads to prevent data corruption.
    

**1.3 – Concurrency Utilities**

- [x]  📊 **Atomic Counter Race** – Implement a counter with `AtomicInteger` and another with `synchronized`, then test both under heavy concurrency.
- [x]  🏁 **Countdown Start** – Simulate a race where multiple threads wait for a start signal using `CountDownLatch`.
- [x]  🚧 **Barrier Sync** – Use `CyclicBarrier` to coordinate threads performing multi-stage tasks that need to wait for each other.
- [x]  🚦 **Semaphore Parking Lot** – Model limited concurrent access to a resource (e.g., parking spaces) with a `Semaphore`.

**1.4 – Deadlocks, Livelocks, Starvation**

- [x]  🪤 **Deadlock Trap** – Write code that causes two threads to deadlock by locking resources in opposite order.
- [x]  🛠 **Deadlock Fixer** – Fix the deadlock by enforcing consistent lock ordering or using `tryLock` with timeouts.
- [x]  🌀 **Livelock Dancers** – Simulate threads that keep yielding to each other without making progress, then fix the livelock.

**1.5 – Task Scheduling & Round Robin**

- [X]  🔁 **Task Carousel** – Implement a low-level Round Robin scheduler using a circular queue, `ReentrantLock`, and `Condition`.
- [X]  📅 **Timed Spinner** – Build a scheduler using `ScheduledExecutorService` and a `ConcurrentLinkedQueue` that runs tasks in rotation.
- [x]  ⏯ **Rotating Player** – Extend your Round Robin scheduler to allow tasks to be added dynamically and support pause/resume functionality.

---

### **Phase 2 – OOP Design Patterns in Java**

**2.1 – Creational Patterns**

- [x]  🗝 **Singleton Vault** – Implement a thread-safe Singleton pattern with double-checked locking and lazy initialization.
- [x]  🏭 **Task Factory** – Create a Factory Method that returns different implementations of Runnable or Callable tasks.
- [x]  🛠 **Scheduler Builder** – Use the Builder pattern to configure and create customizable scheduler instances.

**2.2 – Structural Patterns**

- [x]  🔌 **Task Adapter** – Design an Adapter to make legacy tasks compatible with a new scheduler interface.
- [x]  🎯 **Logging Decorator** – Use the Decorator pattern to add logging around task execution without modifying the tasks themselves.
- [x]  🚪 **Resource Proxy** – Implement a Proxy to control and serialize access to a shared resource, such as a file or database connection.

**2.3 – Behavioral Patterns**

- [x]  📡 **Task Observer** – Use the Observer pattern to notify listeners about task lifecycle events (start, progress, complete).
- [x]  🎟 **Command Queue** – Encapsulate tasks as Command objects and execute them through a queue-based scheduler.
- [x]  🔄 **Strategy Switcher** – Implement the Strategy pattern to dynamically switch between Round Robin and Priority scheduling algorithms at runtime.

---

### **Phase 3 – Integration & Real-World Projects**

**3.1 – Project Ideas**

- [ ]  🌐 **Web Swarm** – Build a concurrent web crawler using `ExecutorService` and `BlockingQueue` with progress updates via Observer pattern.
- [ ]  💬 **Round Robin Chat** – Create a chat server that dispatches messages fairly across multiple chat rooms using Round Robin scheduling.
- [ ]  📈 **Market Pulse** – Simulate a stock market where producer threads update prices and observer threads receive notifications of changes.
- [ ]  ⚖ **API Spinner** – Implement a Round Robin load balancer that distributes incoming API requests evenly across multiple mock servers.

**3.2 – Advanced Topics**

- [ ]  🪵 **ForkJoin Splitter** – Use `ForkJoinPool` to recursively split and process a large computational task (e.g., array summation).
- [ ]  🔗 **Future Chain** – Create a multi-step asynchronous data processing pipeline using `CompletableFuture`chaining.
- [ ]  📦 **Immutable Box** – Design an immutable data structure and demonstrate its thread safety under concurrent acceks.
- [ ]  🌀 **Lock-Free Stack** – Build a thread-safe stack implementation using `AtomicReference` to avoid explicit locks.
