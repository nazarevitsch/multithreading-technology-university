package SecondLab.noparallel;

import SecondLab.Matrix;

public class NoParallelMatrixMultiplication {

    public static int[][] multiplication(int[][] a, int[][] b) throws Exception {
        Matrix.check(a, b);
        int[][] result = Matrix.zeroMatrix(a.length, b[0].length);

        for (int i = 0; i < a.length; i++) {
            for (int l = 0; l < b[i].length; l++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][l] += a[i][k] * b[k][l];
                }
            }
        }
        return result;
    }
}
