package FifthLab;

import java.util.concurrent.ExecutorService;

public class Shop extends Thread {

    private ExecutorService executor;
    private Queue queue;
    private int clientCount;
    private StatisticCollector statistic;

    public Shop(ExecutorService executor, Queue queue, StatisticCollector statistic) {
        this.executor = executor;
        this.queue = queue;
        this.statistic = statistic;
    }


    @Override
    public void run() {
        while (!executor.isShutdown()) {
            try {
                statistic.addFuture(executor.submit(queue.take()));
                if (++clientCount >= Constants.MAX_CUSTOMERS_SIZE) {
                    executor.shutdown();
                    break;
                }
            } catch (InterruptedException e) {}
        }
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}