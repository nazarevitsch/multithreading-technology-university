package ThirdLab.students;

import java.util.concurrent.locks.ReentrantLock;

public class AssistantThread extends Thread {

    private MarkBook markBook;
    private int group;
    private int weeksCount;

    public AssistantThread(MarkBook markBook, int group, int weeksCount) {
        this.markBook = markBook;
        this.group = group;
        this.weeksCount = weeksCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < weeksCount; i++) {
                markBook.putMarksByAssistant(group);
            }
        } catch (InterruptedException e) {
        }
    }
}
