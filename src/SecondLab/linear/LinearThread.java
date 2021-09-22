package SecondLab.linear;

public class LinearThread extends Thread {

    private int[] a;
    private int[][] b;
    private int[] result;

    private int index;

//    private int fromIndex;
//    private int toIndex;

    public LinearThread(int[] a, int[][] b, int[] result, int index) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.index = index;
//        this.fromIndex = fromIndex;
//        this.toIndex = toIndex;
    }

    @Override
    public void run() {
        for (int i = 0; i < a.length; i++) {
            int k = index - i + (index - i < 0 ? b.length : 0);
            for (int l = 0; l < b[i].length; l++) {
                result[l] += a[k] * b[k][l];
            }
        }
    }
}
