package concurrency.scheduling.taskcarousel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CircularQueue<T> {
    private final int capacity;
    private final T[] tasks;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.tasks = (T[]) new Object[capacity];
    }

    T dequeue(){
        lock.lock();
        try{
            // wait if empty
            while(size == 0){
                notEmpty.await();
            }

            T task = tasks[head];
            head = (head + 1) % capacity;
            size--;
            notFull.signal();

            return task;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    void enqueue(T task) {
        lock.lock();
        try{
            // wait if full
            while(size == capacity){
                notFull.await();
            }

            tasks[tail] = task;
            tail = (tail + 1) % capacity;
            size++;
            notEmpty.signal();

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
        }
    }
}
