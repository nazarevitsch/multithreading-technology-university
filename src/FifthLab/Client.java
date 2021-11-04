package FifthLab;

import java.util.concurrent.Callable;

public class Client implements Callable<Result> {

    private Queue queue;
    private long id;

    public Client(Queue queue, long id) {
        System.out.println("CREATED / ID=" + id);
        this.id = id;
        this.queue = queue;
    }

    @Override
    public Result call() {
//        queue.increment();
        System.out.println("START / ID=" + this.id);
        long start = System.currentTimeMillis();
        try {
            Thread.sleep((int) (Math.random() * 5000) + 10000);
        } catch (InterruptedException e) {

        }
        System.out.println("FINISH / ID=" + this.id);
        queue.decrement();
        return new Result((start - System.currentTimeMillis()) / 1000.0);
    }
}
