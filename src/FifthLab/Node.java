package FifthLab;

public class Node {

    private Node next;
    private Client client;

    public Node(Client client) {
        this.client = client;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Client getClient() {
        return client;
    }
}
