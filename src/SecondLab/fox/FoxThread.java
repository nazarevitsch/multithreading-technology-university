package SecondLab.fox;

public class FoxThread extends Thread {


    private Block blockA;
    private Block blockB;
    private Block blockC;

    public FoxThread(Block blockA, Block blockB, Block blockC) {
        this.blockA = blockA;
        this.blockB = blockB;
        this.blockC = blockC;
    }

    @Override
    public void run() {
        for (int i = 0; i < blockA.getMatrix().length; i++) {
            for (int l = 0; l < blockB.getMatrix()[i].length; l++) {
                for (int k = 0; k < blockB.getMatrix().length; k++) {
                    blockC.getMatrix()[i][l] += blockA.getMatrix()[i][k] * blockB.getMatrix()[k][l];
                }
            }
        }
    }

    public Block getBlocksC() {
        return blockC;
    }
}
