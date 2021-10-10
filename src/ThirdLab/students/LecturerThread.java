package ThirdLab.students;

import java.util.concurrent.locks.ReentrantLock;

public class LecturerThread extends Thread {

    private MarkBook markBook;
    private ReentrantLock locker;
    private int weeksCount;

    public LecturerThread(MarkBook markBook, ReentrantLock locker, int weeksCount) {
        this.markBook = markBook;
        this.locker = locker;
        this.weeksCount = weeksCount;
    }

    @Override
    public void run(){

    }
}
