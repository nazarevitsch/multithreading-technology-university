package FifthLab;

import java.util.concurrent.*;

public class Model extends Thread {

    private int modelId;
    private StatisticCollector statistic;

    public Model(int modelId, StatisticCollector statistic) {
        this.modelId = modelId;
        this.statistic = statistic;
    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(Constants.CASHIER_SIZE);
        Queue queue = new Queue();

        ClientProducer clientProducer = new ClientProducer(queue, executor, statistic, modelId);
        clientProducer.start();

        Shop shop = new Shop(executor, queue, statistic);
        shop.start();

        Boolean flag = new Boolean(true);
        FrameThread thread = new FrameThread(statistic, flag);

        thread.start();

        try {
            clientProducer.join();
            shop.join();

            flag = false;
            thread.join();

        } catch (Exception e){

        }
    }
}
