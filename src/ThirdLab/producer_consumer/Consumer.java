package ThirdLab.producer_consumer;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (int number = drop.take(); number >= 0; number = drop.take()) {
            System.out.println("Number:  " + number);
            try {
                Thread.sleep(random.nextInt(55));
            } catch (InterruptedException e) {}
        }
    }
}
