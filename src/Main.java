import FirstLab.billiard.BounceFrame;
import FirstLab.counter.Counter;
import FirstLab.counter.DecrementThread;
import FirstLab.counter.IncrementThread;
import FirstLab.printer.Printer;
import FirstLab.printer.PrinterThread1;
import FirstLab.printer.PrinterThread2;
import SecondLab.fox.FoxMatrixMultiplication;
import SecondLab.linear.LinearMatrixMultiplication;
import SecondLab.Matrix;
import SecondLab.noparallel.NoParallelMatrixMultiplication;

import javax.swing.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws Exception {
//        thirdTest();
        testMatrix();
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

    public static void testMatrix() throws Exception{
        LinearMatrixMultiplication linearMultiplication = new LinearMatrixMultiplication();
        FoxMatrixMultiplication foxMatrixMultiplication = new FoxMatrixMultiplication();

//        int[][] a = {{1,2,3}, {4,5,6}};
//        int[][] b = {{1,2}, {3,4}, {5,6}};

//        int[][] a = {{1,2}, {3,4}, {5,6}};
//        int[][] b = {{1,2,3}, {4,5,6}, {7,8,9}};
//        int[][] b = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
//        int[][] b = {{1,2}, {3,4}};
        int[][] b = Matrix.randomMatrix(1000, 1000, 1, 9);

        long start = System.currentTimeMillis();

//        int[][] resultNo = NoParallelMatrixMultiplication.multiplication(b,b);
//        int[][] resultP1 = linearMultiplication.multiplication(b,b,0);
//        int[][] resultP2 = foxMatrixMultiplication.multiplication(b,b,100, 100);

        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;

        System.out.println("Time: " + t + " sec.");

//        Matrix.print(resultNo);
//        System.out.println();
//        Matrix.print(resultP2);
    }

}
