package ThirdLab.bank;

import ThirdLab.bank.Bank;

import java.util.concurrent.locks.ReentrantLock;

public class TransferThread extends Thread {
    private Bank bank;
    private int fromAccount;
    private int maxAmount;
    private static final int REPS = 1000;

    private ReentrantLock locker;

    public TransferThread(Bank b, int from, int max, ReentrantLock locker) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
        this.locker = locker;
    }

    public void run() {
        try {
            while (!interrupted()) {

                for (int i = 0; i < REPS; i++) {
//                    locker.lock();
                    int toAccount = (int) (bank.size() * Math.random());
                    int amount = (int) (maxAmount * Math.random() / REPS);
                    bank.transfer(fromAccount, toAccount, amount);
//                    locker.unlock();
                    Thread.sleep(1);
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
