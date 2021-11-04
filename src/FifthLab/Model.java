package FifthLab;

import java.util.Vector;
import java.util.concurrent.*;

public class Model {


    public static void model() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Queue queue = new Queue();

        ClientProducer clientProducer = new ClientProducer(queue, executor);
        clientProducer.start();

        Shop shop = new Shop(executor, queue);
        shop.start();
    }
}
