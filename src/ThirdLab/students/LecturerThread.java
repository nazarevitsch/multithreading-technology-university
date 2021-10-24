package ThirdLab.students;

import java.util.concurrent.locks.ReentrantLock;

public class LecturerThread extends Thread {

    private MarkBook markBook;
    private int weeksCount;

    public LecturerThread(MarkBook markBook, int weeksCount) {
        this.markBook = markBook;
        this.weeksCount = weeksCount;
    }

    @Override
    public void run(){
        try {
            for (int i = 0; i < weeksCount; i++) {
                markBook.putMarksByLecturer(i);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e){}
    }
}
