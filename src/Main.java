import FifthLab.Model;
import FirstLab.billiard.BounceFrame;
import FirstLab.counter.Counter;
import FirstLab.counter.DecrementThread;
import FirstLab.counter.IncrementThread;
import FirstLab.printer.Printer;
import FirstLab.printer.PrinterThread1;
import FirstLab.printer.PrinterThread2;
import FourthLab.calculate_statistic.Statistic;
import FourthLab.previous_exercise.ForkJoinMatrixMultiplication;
import FourthLab.search_same_words.SearchSameWordsTaskInFolder;
import FourthLab.search_same_words.WordsOccurringTable;
import FourthLab.search_word.SearchStatement;
import SecondLab.Matrix;
import SecondLab.fox.FoxMatrixMultiplication;
import SecondLab.linear.LinearMatrixMultiplication;
import ThirdLab.bank.TransferThread;
import ThirdLab.bank.Bank;
import ThirdLab.producer_consumer.Consumer;
import ThirdLab.producer_consumer.Drop;
import ThirdLab.producer_consumer.Producer;
import ThirdLab.students.*;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws Exception {
//        testLengthWords();
//        testMatrix();
//        testPrevious();

//        testSearchSameWords();
//        testSearchStatementInDocuments();

//        testModel1();
        testModel2();
//        testModel3();
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
        (new Thread(new Producer(drop, 500))).start();
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
        int[][] b = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
//        int[][] b = Matrix.randomMatrix(2000, 2000, 1, 9);

//        int[][] resultNo = NoParallelMatrixMultiplication.multiplication(b,b);
        int[][] resultP1 = linearMultiplication.multiplication(b,b,0);
//        int[][] resultP2 = foxMatrixMultiplication.multiplication(b,b,100, 100);

//        System.out.println("No parallel:");
//        Matrix.print(resultNo);
//        System.out.println();
//        System.out.println("Linear algorithm:");
        Matrix.print(resultP1);
        System.out.println();
//        System.out.println("Fox algorithm:");
//        Matrix.print(resultP2);
    }

    public static void testLengthWords() {
//        Statistic lengthCount = new Statistic(new File("src/FourthLab/calculate_statistic/test_text.txt"));
        Statistic lengthCount = new Statistic(new File("src/FourthLab/calculate_statistic/master_and_margarita.txt"));

        lengthCount.calculateOneThread();
        lengthCount.calculateMultiThreads();
    }

    public static void testPrevious() throws Exception{
        ForkJoinMatrixMultiplication forkJoinMatrixMultiplication = new ForkJoinMatrixMultiplication();
        int[][] b = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
//        int[][] b = Matrix.randomMatrix(2000, 2000, 1, 9);

        int[][] result = forkJoinMatrixMultiplication.multiplication(b, b, 1);

        Matrix.print(result);
    }

    public static void testSearchSameWords() {
        SearchSameWordsTaskInFolder search = new SearchSameWordsTaskInFolder(new File("src/FourthLab/search_same_words/test"));

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        WordsOccurringTable table = forkJoinPool.invoke(search);

        System.out.println("OCCURRING: " + table.get("on").size() + " times.");
        System.out.println(table.get("on"));
//        System.out.println(table.getKeys());
    }

    public static void testSearchStatementInDocuments() {
//        String searchedStatement = "you were there";
        String searchedStatement = "search of the Lower City";
        File file1 = new File("src/FourthLab/search_word/test");
//        File file2 = new File("src/FourthLab/search_word/test/new_test.txt");

        SearchStatement searchStatement = new SearchStatement(searchedStatement, file1);

        System.out.println("Statement: " + searchedStatement);
        SearchStatement.print(searchStatement.search(), 0.7);
    }

    public static void testModel1() {
        FifthLab.StatisticCollector statistic1 = new FifthLab.StatisticCollector();

        Model model1 = new Model(1, statistic1);
        model1.start();

        try {
            model1.join();
            System.out.println("IMITATION N1: \n" + statistic1.collect());
        } catch (Exception e) {}
    }

    public static void testModel2() {
        FifthLab.StatisticCollector statistic1 = new FifthLab.StatisticCollector();
        FifthLab.StatisticCollector statistic2 = new FifthLab.StatisticCollector();
        FifthLab.StatisticCollector statistic3 = new FifthLab.StatisticCollector();
        FifthLab.StatisticCollector statistic4 = new FifthLab.StatisticCollector();


        Model model1 = new Model(1, statistic1);
        Model model2 = new Model(2, statistic2);
        Model model3 = new Model(3, statistic3);
        Model model4 = new Model(4, statistic4);

        model1.start();
        model2.start();
        model3.start();
        model4.start();

        try {
            model1.join();
            System.out.println("\nIMITATION N1:" + statistic1.collect());

            model2.join();
            System.out.println("\nIMITATION N2:" + statistic2.collect());

            model3.join();
            System.out.println("\nIMITATION N3:" + statistic3.collect());

            model4.join();
            System.out.println("\nIMITATION N4:" + statistic4.collect());
        } catch (Exception e) {}
    }

    public static void testModel3() {
        FifthLab.StatisticCollector statistic1 = new FifthLab.StatisticCollector();

        Model model1 = new Model(1, statistic1);
        model1.start();

        try {
            model1.join();
            System.out.println(statistic1.collect());
        } catch (Exception e) {}
    }
}
