package SecondLab.linear;

import SecondLab.Matrix;

public class LinearThread extends Thread {

    private Matrix a;
    private Matrix b;
    private Matrix result;
    private int fromIndex;
    private int toIndex;

    public LinearThread(Matrix a, Matrix b, Matrix result, int fromIndex, int toIndex) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public void run() {
        for (int i = fromIndex; i < toIndex; i++) {
            for (int l = 0; l < a.getWidth(); l++) {
                int sum = 0;
                for (int k = 0; k < b.getHeight(); k++) {
                    sum += (int) (a.getElement(i, k)) * (int) (b.getElement(k, l));
//                    System.out.println("I: " + i + ", L: " + l + ", K: " + k);
                }
//                System.out.println("SUM: " + sum);
                result.setElement(i, l, sum);
            }
        }
    }
}
