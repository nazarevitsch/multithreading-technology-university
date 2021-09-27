package SecondLab.linear;

import SecondLab.Matrix;

public class LinearMatrixMultiplication {

    public int[][] multiplication(int[][] a, int[][] b, int threadsSize) throws Exception {
        Matrix.check(a, b);
        int[][] result = Matrix.zeroMatrix(a.length, b[0].length);
        LinearThread linearThread = null;

        long start = System.currentTimeMillis();
        if (threadsSize <= 0) threadsSize = a.length;

        int rowsPerThread = a.length / threadsSize;
        for (int i = 0; i < threadsSize; i++) {
            linearThread = new LinearThread(b, rowsPerThread, rowsPerThread * i, rowsPerThread * (i + 1));
            for (int l = 0; l < rowsPerThread; l++) {
                linearThread.setRowAAndC(a[rowsPerThread * i + l], result[rowsPerThread * i + l], l);
            }
            linearThread.start();
        }
        linearThread.join();
        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;
        System.out.println("Time Linear: " + t + " sec.");
        return result;
    }

    //        for (int i = 0; i < a.length; i++) {
//            linearThread = new LinearThread(a[i], b, result[i], i);
//            linearThread.start();
//        }

//    public int[][] multiplication(int[][] a, int[][] b, int threadsSize) throws Exception {
//        Matrix.check(a, b);
//        int[][] result = Matrix.zeroMatrix(a.length, b[0].length);
//        LinearThread[] linearThreads = new LinearThread[a.length];
//
////        int mainSize = a[0].length / threadsSize;
////        int additionalSize = a[0].length % threadsSize;
////        int from = (mainSize * i) + ((i + 1) > additionalSize ? additionalSize : i);
////        int to = (mainSize * (i + 1)) + ((i + 1) > additionalSize ? additionalSize : (i + 1));
//
//        int k = 0;
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(i);
//            for (int l = 0; l < b.length; l++) {
//
//                k = l - i < 0 ? b.length + l - i : l - i;
//                linearThreads[l] = new LinearThread(a[l], b[k], result[l], k);
//                linearThreads[l].start();
//            }
//        }
//
//        for (int i = 0; i < a.length; i++) {
//            linearThreads[i].join();
//        }
//        return result;
//    }
}
