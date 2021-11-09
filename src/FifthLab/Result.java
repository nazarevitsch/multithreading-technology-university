package FifthLab;

public class Result {

    private double timePerClient;
    private int queueSize;

    public Result(double timePerClient, int queueSize) {
        this.timePerClient = timePerClient;
        this.queueSize = queueSize;
    }

    public double getTime() {
        return timePerClient;
    }

    public void setTime(double timePerClient) {
        this.timePerClient = timePerClient;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }
}
