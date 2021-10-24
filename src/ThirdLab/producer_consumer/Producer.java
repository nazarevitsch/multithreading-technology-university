package ThirdLab.producer_consumer;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private int size;

    public Producer(Drop drop, int size) {
        this.drop = drop;
        this.size = size;
    }

    public void run() {
        int[] numbers = new int[size];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            drop.put(numbers[i]);
            try {
                Thread.sleep(random.nextInt(50));
            } catch (InterruptedException e) {}
        }
        drop.put(-1);
    }
}
