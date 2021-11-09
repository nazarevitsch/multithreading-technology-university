package FifthLab;

public class FrameThread extends Thread{

    private StatisticCollector statisticCollector;
    private Boolean flag;

    public FrameThread(StatisticCollector statisticCollector, Boolean flag) {
        this.statisticCollector = statisticCollector;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(1200);
                System.out.println(statisticCollector.collectTemporal());
            } catch (Exception e) {}
        }
        System.out.println("NO");
    }

}
