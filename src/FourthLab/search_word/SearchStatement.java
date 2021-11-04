package FourthLab.search_word;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class SearchStatement {

    public static String[] SEARCHED_STATEMENT;
    public static double[] PERCENTAGES;
    private File currentFile;

    public SearchStatement(String searchedStatement, File currentFile) {
        this.currentFile = currentFile;
        SEARCHED_STATEMENT = searchedStatement.toLowerCase().split(" ");

        PERCENTAGES = new double[SEARCHED_STATEMENT.length];
        double sum = Arrays.stream(SEARCHED_STATEMENT).mapToInt(String::length).sum();
        for (int i = 0; i < PERCENTAGES.length; i++) {
            PERCENTAGES[i] = (double) SEARCHED_STATEMENT[i].length() / sum;
//            System.out.println(SEARCHED_STATEMENT[i] + " = " + PERCENTAGES[i]);
        }
    }

    public List<WordOccurring> search() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(new SearchStatementInFolderTask(this.currentFile));
    }

    public static void print(List<WordOccurring> occurring, double percentageFilter) {
        System.out.println("SIZE: " + occurring.size());
        occurring.stream().filter(el -> el.getPercentage() >= percentageFilter).forEach(System.out::println);
    }
}
