package SecondLab.linear;

import SecondLab.Matrix;

public class LinearMatrixMultiplication {

    public int[][] multiplication(int[][] a, int[][] b, int threadsSize) throws Exception {
        Matrix.check(a, b);
        int[][] result = Matrix.zeroMatrix(a.length, b[0].length);
        LinearThread linearThread = null;

//        int mainSize = a[0].length / threadsSize;
//        int additionalSize = a[0].length % threadsSize;

        for (int i = 0; i < a.length; i++) {
//            int from = (mainSize * i) + ((i + 1) > additionalSize ? additionalSize : i);
//            int to = (mainSize * (i + 1)) + ((i + 1) > additionalSize ? additionalSize : (i + 1));
            linearThread = new LinearThread(a[i], b, result[i], i);
            linearThread.start();
        }
        linearThread.join();
        return result;
    }
}
