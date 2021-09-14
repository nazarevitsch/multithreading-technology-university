package FirstLab.counter;

public class DecrementThread extends Thread {

    private Counter counter;

    public DecrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            try {
                counter.decrement();
            } catch (InterruptedException e) {

            }
        }
    }
}
