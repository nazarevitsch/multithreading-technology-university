package FourthLab.search_same_words;

public class Occurring {

    private int line;
    private String fileName;

    public Occurring(int line, String fileName) {
        this.line = line;
        this.fileName = fileName;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Occurring{" +
                "line=" + line +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
