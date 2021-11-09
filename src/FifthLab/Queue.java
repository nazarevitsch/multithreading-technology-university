package FifthLab;

public class Queue {

    private Node first;
    private Node last;

    private int size;
    private int usedTasks;

    public synchronized Client take() throws InterruptedException{
        while (size == 0 || usedTasks == Constants.CASHIER_SIZE) {
            wait();
        }
        Client client = first.getClient();
        first = first.getNext();
        this.usedTasks++;
        this.size--;
        return client;
    }

    public synchronized void put(Client client) {
        if (first == null) {
            first = new Node(client);
            last = first;
        } else {
            last.setNext(new Node(client));
            last = last.getNext();
        }
        this.size++;
        notifyAll();
    }

    public synchronized int size() {
        return this.size;
    }

    public synchronized void decrement() {
        usedTasks--;
        notifyAll();
    }
}
