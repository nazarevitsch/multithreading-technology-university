package FirstLab.counter;

public class IncrementThread extends Thread {

    private Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            try {
                counter.increment();
            } catch (InterruptedException e) {

            }
        }
    }
}
