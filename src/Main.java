import FirstLab.billiard.BounceFrame;
import FirstLab.counter.Counter;
import FirstLab.counter.DecrementThread;
import FirstLab.counter.IncrementThread;
import FirstLab.printer.Printer;
import FirstLab.printer.PrinterThread1;
import FirstLab.printer.PrinterThread2;
import SecondLab.linear.LinearMatrixMultiplication;
import SecondLab.Matrix;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        firstTest();
//        secondTest();
//        thirdTest();
//        testMatrix1();
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

        IncrementThread incrementThread = new IncrementThread(counter);
        DecrementThread decrementThread = new DecrementThread(counter);

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Result: " + counter.getNumber());
    }

    public static void testMatrix1() throws Exception{
        LinearMatrixMultiplication multiplication = new LinearMatrixMultiplication();

        Integer[][] m = {{1,2,3,4,5,6,7},
                {8,9,10,11,12,13,14},
                {15,16,17,18,19,20,21},
                {22,23,24,25,26,27,28},
                {29,30,31,32,33,34,35},
                {36,37,38,39,40,41,42},
                {43,44,45,46,47,48,49}};
        Matrix<Integer> matrix = new Matrix(m);

//        Matrix matrix = Matrix.randomMatrix(100, 100, 1, 9);

        Matrix r = multiplication.multiplication(matrix, matrix, 4);
    }
}
