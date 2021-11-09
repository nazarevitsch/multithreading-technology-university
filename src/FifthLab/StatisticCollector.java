package FifthLab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class StatisticCollector {

    private int rejectedCount;
    private List<Future<Result>> results;

    public StatisticCollector() {
        results = new ArrayList<>();
    }

    public void increaseRejectCount() {
        rejectedCount++;
    }

    public void addFuture(Future<Result> result) {
        results.add(result);
    }

    public Statistic collect() {
        Statistic statistic = new Statistic();
        statistic.setRejectedCount(this.rejectedCount);
        statistic.setRejectedPercentage((double) (rejectedCount * 100) / (rejectedCount + results.size()));

        statistic.setClientsSize(results.size());

        statistic.setMiddleTimePerClient(results.stream().mapToDouble(el -> {
            try {
                return el.get().getTime();
            } catch (Exception e){
                System.out.println("--- ERROR ---");
                return 0;
            }
        }).sum() / results.size());

        statistic.setMiddleSizeOfQueue(results.stream().mapToInt(el -> {
            try {
                return el.get().getQueueSize();
            } catch (Exception e){
                System.out.println("--- ERROR ---");
                return 0;
            }
        }).sum() / results.size());

        return statistic;
    }

    public Statistic collectTemporal() throws Exception{
        Statistic statistic = new Statistic();
        statistic.setRejectedCount(this.rejectedCount);

        int size = 0;

        double sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).isDone()) {
                size++;
                sum2 += results.get(i).get().getQueueSize();
                sum1 += results.get(i).get().getTime();
            }
        }

        statistic.setRejectedPercentage((double) (rejectedCount * 100) / (rejectedCount + size));
        statistic.setClientsSize(size);
        statistic.setMiddleTimePerClient(sum1 / size);
        statistic.setMiddleSizeOfQueue(sum2 / size);

        return statistic;
    }
}
