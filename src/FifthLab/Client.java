package FifthLab;

import java.util.concurrent.Callable;

public class Client implements Callable<Result> {

    private Queue queue;
    private long id;
    private int modelId;

    public Client(Queue queue, long id, int modelId) {
        this.id = id;
        this.queue = queue;
        this.modelId = modelId;
        System.out.println(id + " / " + modelId + " = CREATED");
    }

    @Override
    public Result call() {
        System.out.println(id + " / " + modelId + " = START");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep((int) (Math.random() * (Constants.TIME_CASHIER_TO_MILLISECONDS
                    - Constants.TIME_CASHIER_FROM_MILLISECONDS))
                    + Constants.TIME_CASHIER_FROM_MILLISECONDS);
        } catch (InterruptedException e) {}

        System.out.println(id + " / " + modelId + " = FINISH");
        queue.decrement();
        return new Result((System.currentTimeMillis() - start) / 1000.0, queue.size());
    }
}
