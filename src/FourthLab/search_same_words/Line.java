package FourthLab.search_same_words;

public class Line {
    private String line;
    private int numberOfLine;

    public Line(String line, int numberOfLine) {
        this.line = line;
        this.numberOfLine = numberOfLine;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getNumberOfLine() {
        return numberOfLine;
    }

    public void setNumberOfLine(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }
}
