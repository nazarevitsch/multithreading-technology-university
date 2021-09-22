package FirstLab.printer;

public class Printer {

    private boolean flag = true;

    public synchronized void print1() throws InterruptedException {
        if (flag) {
            wait();
        }
        System.out.println("|");
        flag = true;
        notifyAll();
    }

    public synchronized void print2() throws InterruptedException {
        if (!flag) {
            wait();
        }
        System.out.println("-");
        flag = false;
        notifyAll();
    }

    public synchronized void print(String line) {
        System.out.println(line);
    }
}
