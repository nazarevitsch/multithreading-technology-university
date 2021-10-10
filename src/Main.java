import FirstLab.billiard.BounceFrame;
import FirstLab.counter.Counter;
import FirstLab.counter.DecrementThread;
import FirstLab.counter.IncrementThread;
import FirstLab.printer.Printer;
import FirstLab.printer.PrinterThread1;
import FirstLab.printer.PrinterThread2;
import SecondLab.fox.FoxMatrixMultiplication;
import SecondLab.linear.LinearMatrixMultiplication;
import ThirdLab.bank.TransferThread;
import ThirdLab.bank.Bank;
import ThirdLab.producer_consumer.Consumer;
import ThirdLab.producer_consumer.Drop;
import ThirdLab.producer_consumer.Producer;
import ThirdLab.students.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws Exception {
//        bankTest();
//        producerConsumerTest();
        markBookTest();
    }

    public static void firstTest() {
        BounceFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " + Thread.currentThread().getName());
    }

    public static void secondTest() {
        Printer printer = new Printer();

        PrinterThread1 printerThread1 = new PrinterThread1(printer);
        printerThread1.start();

        PrinterThread2 printerThread2 = new PrinterThread2(printer);
        printerThread2.start();
    }

    public static void thirdTest() {
        Counter counter = new Counter();

        ReentrantLock locker = new ReentrantLock();

        IncrementThread incrementThread = new IncrementThread(counter, locker);
        DecrementThread decrementThread = new DecrementThread(counter, locker);

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Result: " + counter.getNumber());
    }

    public static void testMatrix() throws Exception {
        LinearMatrixMultiplication linearMultiplication = new LinearMatrixMultiplication();
        FoxMatrixMultiplication foxMatrixMultiplication = new FoxMatrixMultiplication();

//        int[][] b = {{1,2,3}, {4,5,6}, {7,8,9}};
//        int[][] b = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
//        int[][] b = Matrix.randomMatrix(4000, 4000, 1, 9);

//        int[][] resultNo = NoParallelMatrixMultiplication.multiplication(b,b);
//        int[][] resultP1 = linearMultiplication.multiplication(b,b,10);
//        int[][] resultP2 = foxMatrixMultiplication.multiplication(b,b,400, 400);

//        System.out.println("No parallel:");
//        Matrix.print(resultNo);
//        System.out.println();
//        System.out.println("Linear algorithm:");
//        Matrix.print(resultP1);
//        System.out.println();
//        System.out.println("Fox algorithm:");
//        Matrix.print(resultP2);
    }

    public static void bankTest() {
        int NACCOUNTS = 10;
        int INITIAL_BALANCE = 10000;

        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        ReentrantLock locker = new ReentrantLock();

        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            TransferThread t = new TransferThread(b, i, INITIAL_BALANCE, locker);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start();
        }
    }

    public static void producerConsumerTest() {
        Drop drop = new Drop();
        (new Thread(new Producer(drop, 100))).start();
        (new Thread(new Consumer(drop))).start();
    }

    public static void markBookTest() {
        int numberOfGroup = 3;
        int numberOfStudentsInGroup = 6;

        int WEEKS = 5;

        MarkBook markBook = new MarkBook(numberOfGroup);
        for (int i = 0; i < numberOfGroup; i++) {
            List<Student> students = new ArrayList<>();
            for (int l = 0; l < numberOfStudentsInGroup; l++) {
                students.add(new Student("Student G" + i + "-N" + l));
            }
            markBook.putGroup(new Group(students));
        }

        for (int i = 0; i < numberOfGroup; i++) {
            new AssistantThread(markBook, i, WEEKS).start();
        }
        new LecturerThread(markBook, WEEKS).start();
    }
}
