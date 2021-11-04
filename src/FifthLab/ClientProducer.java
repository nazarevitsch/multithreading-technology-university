package FifthLab;

import java.util.concurrent.ExecutorService;

public class ClientProducer extends Thread {

    private Queue queue;
    private int count;
    private  ExecutorService executor;
    private int rejected;

    public ClientProducer(Queue queue, ExecutorService executor) {
        this.queue = queue;
        this.executor = executor;
    }

    @Override
    public synchronized void run() {
        while (count < 30) {
            try {
                Thread.sleep((int) (Math.random() * 1000) + 1500);
                if (queue.size() < 5) {
                    queue.put(new Client(queue, ++count));
                } else {
                    rejected++;
                }
            } catch (InterruptedException e) {}
        }
        executor.shutdown();
        System.out.println("==========  " + rejected + "  ==========");
    }
}
