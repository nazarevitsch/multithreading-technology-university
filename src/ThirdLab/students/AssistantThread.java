package ThirdLab.students;

import java.util.concurrent.locks.ReentrantLock;

public class AssistantThread extends Thread {

    private MarkBook markBook;
    private int group;
    private ReentrantLock locker;
    private int weeksCount;

    public AssistantThread(MarkBook markBook, int group, ReentrantLock locker, int weeksCount) {
        this.markBook = markBook;
        this.group = group;
        this.locker = locker;
        this.weeksCount = weeksCount;
    }

    @Override
    public void run(){

    }
}
