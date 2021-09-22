package FirstLab.counter;

import java.util.concurrent.locks.ReentrantLock;

public class IncrementThread extends Thread {

    private Counter counter;
    private ReentrantLock locker;

    public IncrementThread(Counter counter, ReentrantLock locker) {
        this.counter = counter;
        this.locker = locker;
    }

    @Override
    public void run() {
        try {
            locker.lock();
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        } catch (InterruptedException e) {

        } finally {
            locker.unlock();
        }
    }
}
