package SecondLab.max;

import SecondLab.Matrix;
import SecondLab.linear.LinearThread;

public class FoxMatrixMultiplication {

    public int[][] multiplication(int[][] a, int[][] b, int threadsSize) throws Exception {
        Matrix.check(a, b);
        int[][] result = Matrix.zeroMatrix(a.length, b[0].length);
        FoxThread foxThread = null;

        for (int i = 0; i < a.length; i++) {
//            linearThread = new LinearThread(a[i], b, result[i], i);
//            linearThread.start();
        }

        foxThread.join();
        return result;
    }
}
