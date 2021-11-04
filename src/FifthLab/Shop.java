package FifthLab;

import java.util.concurrent.ExecutorService;

public class Shop extends Thread {

    private ExecutorService executor;
    private Queue queue;

    public Shop(ExecutorService executor, Queue queue) {
        this.executor = executor;
        this.queue = queue;
    }


    @Override
    public void run() {
        while (!executor.isShutdown()) {
            try {
                executor.submit(queue.take());
            } catch (InterruptedException e) {}
        }
    }
}