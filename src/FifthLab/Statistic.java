package FifthLab;

public class Statistic {

    private int rejectedCount;
    private double rejectedPercentage;
    private double middleTimePerClient;
    private int middleSizeOfQueue;
    private int clientsSize;

    public Statistic() {
    }

    public int getRejectedCount() {
        return rejectedCount;
    }

    public void setRejectedCount(int rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    public double getRejectedPercentage() {
        return rejectedPercentage;
    }

    public void setRejectedPercentage(double rejectedPercentage) {
        this.rejectedPercentage = rejectedPercentage;
    }

    public double getMiddleTimePerClient() {
        return middleTimePerClient;
    }

    public void setMiddleTimePerClient(double middleTimePerClient) {
        this.middleTimePerClient = middleTimePerClient;
    }

    public int getMiddleSizeOfQueue() {
        return middleSizeOfQueue;
    }

    public void setMiddleSizeOfQueue(int middleSizeOfQueue) {
        this.middleSizeOfQueue = middleSizeOfQueue;
    }

    public int getClientsSize() {
        return clientsSize;
    }

    public void setClientsSize(int clientsSize) {
        this.clientsSize = clientsSize;
    }

    @Override
    public String toString() {
        return "\n-- Clients Size: " + clientsSize
                + "\n-- Rejected: " + rejectedCount + ", Percentage: " + rejectedPercentage
                + "\n-- Middle Time per Client: " + middleTimePerClient
                + "\n-- Middle size of Queue: " + middleSizeOfQueue
                + "\n";
    }
}
