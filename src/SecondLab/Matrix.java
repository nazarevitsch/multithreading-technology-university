package SecondLab;

public class Matrix<T> {

    private T type;
    private T[][] matrix;

    public Matrix(T[][] matrix) {
        this.matrix = matrix;
    }

    public T getElement(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex];
    }

    public void setElement(int rowIndex, int columnIndex, T element) {
        matrix[rowIndex][columnIndex] = element;
    }

    public int getHeight() {
        return matrix.length;
    }

    public int getWidth() {
        return matrix[0].length;
    }

    public static Matrix randomMatrix(int rowSize, int columnSize, int from, int to) {
        Integer matrix[][] = new Integer[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            matrix[i] = new Integer[columnSize];
            for (int l = 0; l < columnSize; l++) {
                matrix[i][l] = (int) (Math.random() * (to - from)) + from;
            }
        }
        return new Matrix(matrix);
    }

    public static Matrix zeroMatrix(int rowSize, int columnSize) {
        Integer matrix[][] = new Integer[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            matrix[i] = new Integer[columnSize];
            for (int l = 0; l < columnSize; l++) {
                matrix[i][l] = 0;
            }
        }
        return new Matrix(matrix);
    }

    public static void print(Matrix matrix) {
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int l = 0; l < matrix.getWidth(); l++) {
                System.out.print(matrix.getElement(i, l) + "  ");
            }
            System.out.println();
        }
    }
}
