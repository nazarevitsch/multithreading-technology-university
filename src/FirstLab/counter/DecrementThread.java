package FirstLab.counter;

import java.util.concurrent.locks.ReentrantLock;

public class DecrementThread extends Thread {

    private Counter counter;
    private ReentrantLock locker;

    public DecrementThread(Counter counter, ReentrantLock locker) {
        this.counter = counter;
        this.locker = locker;
    }

    @Override
    public void run() {
        try {
            locker.lock();
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
            }
        } catch (InterruptedException e) {
            System.out.println(1);
        } finally {
            locker.unlock();
        }
    }
}
