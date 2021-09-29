package SecondLab.fox;

import SecondLab.Matrix;

public class FoxMatrixMultiplication {

    public int[][] multiplication(int[][] a, int[][] b, int blockHeight, int blockWidth) throws Exception {
        Matrix.check(a, b);

        Block[][] blocksA = divideToBlocks(a, blockHeight, blockWidth);
        Block[][] blocksB = divideToBlocks(b, blockHeight, blockWidth);
        Block[][] blocksC = divideToBlocks(Matrix.zeroMatrix(a.length, b[0].length), blockHeight, blockWidth);

        FoxThread[][] foxThreads = new FoxThread[a.length / blockHeight][];
        for (int i = 0; i < blocksC.length; i++) {
            foxThreads[i] = new FoxThread[blocksC[i].length];
        }

        long start = System.currentTimeMillis();
        boolean firstLoop = false;
        for (int stage = 0; stage < foxThreads.length; stage++) {
            for (int i = 0; i < foxThreads.length; i++) {
                for (int l = 0; l < foxThreads[i].length; l++) {
                    if (firstLoop) {
                        foxThreads[i][l].join();
                    }
                    int k = i + stage > foxThreads.length - 1 ? i + stage - foxThreads.length : i + stage;
                    foxThreads[i][l] = new FoxThread(blocksA[i][k], blocksB[k][l], blocksC[i][l]);
                    foxThreads[i][l].start();
                }
            }
            firstLoop = true;
        }

        for (int i = 0; i < foxThreads.length; i++) {
            for (int l = 0; l < foxThreads[i].length; l++) {
                foxThreads[i][l].join();
            }
        }
        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;
        System.out.println("Time FOX: " + t + " sec.");
        return unionBlocks(blocksC, blockHeight);
    }

    private Block[][] divideToBlocks(int[][] matrix, int blockHeight, int blockWidth) throws Exception {
        if (matrix.length % blockHeight != 0 || matrix[0].length % blockWidth != 0) {
            throw new Exception("Error!");
        }

        Block[][] dividedMatrix = new Block[matrix.length / blockHeight][];

        for (int i = 0; i < dividedMatrix.length; i++) {
            dividedMatrix[i] = new Block[matrix[i].length / blockWidth];
            for (int l = 0; l < dividedMatrix[i].length; l++) {
                int[][] matrixSmall = new int[blockHeight][blockWidth];
                for (int j = blockHeight * i; j < blockHeight * (i + 1); j++) {
                    for (int k = blockHeight * l; k < blockWidth * (l + 1) ; k++) {
                        matrixSmall[j - (blockHeight * i)][k - (blockHeight * l)] = matrix[j][k];
                    }
                }
                dividedMatrix[i][l] = new Block(matrixSmall);
            }
        }
        return dividedMatrix;
    }

    private int[][] unionBlocks(Block[][] blocks, int size) {
        int[][] unitedMatrix = Matrix.zeroMatrix(blocks.length * size, blocks.length * size);

        for (int i = 0; i < blocks.length; i++) {
            for (int l = 0; l < blocks[i].length; l++) {
                for (int j = size * i; j < size * (i + 1); j++) {
                    for (int k = size * l; k < size * (l + 1); k++) {
                        unitedMatrix[j][k] = blocks[i][l].getMatrix()[j - (size * i)][k - (size * l)];
                    }
                }
            }
        }
        return unitedMatrix;
    }
}
