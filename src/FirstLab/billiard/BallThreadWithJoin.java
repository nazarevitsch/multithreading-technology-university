package FirstLab.billiard;

public class BallThreadWithJoin extends BallThread {

    private Thread threadForWaiting;

    public BallThreadWithJoin(Ball ball, Thread threadForWaiting) {
        super(ball);
        this.threadForWaiting = threadForWaiting;
    }

    @Override
    public void run() {
        try {

            threadForWaiting.join();

            for (int i = 1; i < 10000; i++) {
                if (getB().move()) {
                    break;
                }
                System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
        }
    }
}
