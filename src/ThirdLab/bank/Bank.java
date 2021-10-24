package ThirdLab.bank;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {
    public static final int NTEST = 10000;

//    private final AtomicInteger[] accounts;
//    private final AtomicLong ntransacts;

    private final int[] accounts;
    private long ntransacts;

    private boolean[] flags;

    public Bank(int n, int initialBalance) {
//        accounts = new AtomicInteger[n];
//        for (int i = 0; i < accounts.length; i++) {
//            accounts[i] = new AtomicInteger(initialBalance);
//        }
//        ntransacts = new AtomicLong(0);
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);

        flags = new boolean[n];
        flags[0] = true;
    }

    public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
//        accounts[from].addAndGet(-amount);
//        accounts[to].addAndGet(amount);
//        if (ntransacts.incrementAndGet() % NTEST == 0){
//            test(from);
//        }

        while (!flags[from]) {
            wait();
        }

        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0)
            test(from);

        changeFlags(from);
        notifyAll();
    }

    public void test(int from) {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            sum += accounts[i];
//            sum += accounts[i].get();
        }
        System.out.println("From: " + from + " Transactions:" + ntransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }

    public void changeFlags(int from) {
        if (from == flags.length - 1) {
            flags[from] = false;
            flags[0] = true;
        } else {
            flags[from] = false;
            flags[from + 1] = true;
        }
    }
}

