package FifthLab;

import java.util.concurrent.ExecutorService;

public class FrameThread extends Thread{

    private StatisticCollector statisticCollector;
    private ExecutorService executor;
    private int modelId;

    public FrameThread(StatisticCollector statisticCollector, ExecutorService executor, int modelId) {
        this.statisticCollector = statisticCollector;
        this.executor = executor;
        this.modelId = modelId;
    }

    @Override
    public void run() {
        while (!executor.isShutdown()) {
            try {
                Thread.sleep(1300);
                System.out.println("\n---- Frame Statistic for model: " + modelId +  statisticCollector.collectTemporal());
            } catch (Exception e) {}
        }
    }

}
