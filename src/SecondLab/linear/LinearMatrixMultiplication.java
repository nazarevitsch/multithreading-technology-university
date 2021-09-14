package SecondLab.linear;

import SecondLab.Matrix;

public class LinearMatrixMultiplication {

    public Matrix multiplication(Matrix a, Matrix b, int threadsSize) throws Exception {
        check(a, b);
        Matrix result = Matrix.zeroMatrix(a.getHeight(), b.getWidth());
        LinearThread linearThread = null;

        int mainSize = a.getWidth() / threadsSize;
        int additionalSize = a.getWidth() % threadsSize;

        for (int i = 0; i < threadsSize; i++) {
            int from = (mainSize * i) + ((i + 1) > additionalSize ? additionalSize : i);
            int to = (mainSize * (i + 1)) + ((i + 1) > additionalSize ? additionalSize : (i + 1));
            linearThread = new LinearThread(a, b, result, from, to);
            linearThread.start();
        }
        linearThread.join();
        return result;
    }

    private void check(Matrix a, Matrix b) throws Exception{
        if (a.getWidth() != b.getHeight() || a.getHeight() != b.getWidth()) {
            throw new Exception("EXCEPTION");
        }
    }
}
