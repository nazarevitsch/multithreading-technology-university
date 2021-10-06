package ThirdLab.producer_consumer;

public class Drop {
    private int numberForMessage;
    private boolean empty = true;

    public synchronized int take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = true;
        notifyAll();
        return numberForMessage;
    }

    public synchronized void put(int numberForMessage) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = false;
        this.numberForMessage = numberForMessage;
        notifyAll();
    }
}
