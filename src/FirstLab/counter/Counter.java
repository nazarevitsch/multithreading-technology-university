package FirstLab.counter;

public class Counter {
    private int number;
    private boolean flag;

    public synchronized void increment() throws InterruptedException {
//        if (!flag) {
//            wait();
//        }
        number++;
//        flag = false;
//        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException{
//        if (flag) {
//            wait();
//        }
        number--;
//        flag = true;
//        notifyAll();
    }

    public int getNumber() {
        return number;
    }
}
