package FifthLab;

import java.util.concurrent.ExecutorService;

public class ClientProducer extends Thread {

    private Queue queue;
    private int count;
    private  ExecutorService executor;
    private StatisticCollector statistic;
    private int modelId;

    public ClientProducer(Queue queue, ExecutorService executor, StatisticCollector statistic, int modelId) {
        this.queue = queue;
        this.executor = executor;
        this.statistic = statistic;
        this.modelId = modelId;
    }

    @Override
    public synchronized void run() {
        while (!executor.isShutdown()) {
            try {
                Thread.sleep((int) (Math.random() *
                        (Constants.TIME_CLIENT_PRODUCER_TO_MILLISECONDS
                                - Constants.TIME_CLIENT_PRODUCER_FROM_MILLISECONDS))
                        + Constants.TIME_CLIENT_PRODUCER_FROM_MILLISECONDS);

                if (queue.size() < Constants.MAX_QUEUE_SIZE) {
                    queue.put(new Client(queue, ++count, modelId));
                } else {
                    statistic.increaseRejectCount();
                }
            } catch (InterruptedException e) {}
        }
    }
}
