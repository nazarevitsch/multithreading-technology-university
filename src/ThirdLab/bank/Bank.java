package ThirdLab.bank;

import java.util.Arrays;

public class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts;

    public Bank(int n, int initialBalance) {
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, int amount) throws InterruptedException {
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0)
            test(from);
    }

    public void test(int from) {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i];
        System.out.println("From: " + from + " Transactions:" + ntransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }
}

