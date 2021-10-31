package FourthLab.search_word;

import FourthLab.search_same_words.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class SearchStatementInDocumentTask extends RecursiveTask<List<WordOccurring>> {

    private static final int LINES_PER_TASK = 10;
    private List<Line> lines;
    private String currentFileName;

    public SearchStatementInDocumentTask(List<Line> lines, String currentFileName) {
        this.lines = lines;
        this.currentFileName = currentFileName;
    }

    @Override
    protected List<WordOccurring> compute() {
        if (lines.size() > LINES_PER_TASK) {
            List<SearchStatementInDocumentTask> list = new ArrayList<>(SearchStatementInDocumentTask.invokeAll(createSubTasks()));
            List<WordOccurring> occurring = list.get(0).join();
            for (int i = 1; i < list.size(); i++) {
                occurring.addAll(list.get(i).join());
            }
            return occurring;
        } else {
            return calculate();
        }
    }

    private Collection<SearchStatementInDocumentTask> createSubTasks() {
        List<SearchStatementInDocumentTask> list = new ArrayList<>();
        list.add(new SearchStatementInDocumentTask(lines.subList(0, lines.size() / 2), currentFileName));
        list.add(new SearchStatementInDocumentTask(lines.subList(lines.size() / 2, lines.size()), currentFileName));
        return list;
    }

    private List<WordOccurring> calculate() {
        List<WordOccurring> occurring = new ArrayList<>();
        List<String> words;

        WordOccurring wordOccurring = new WordOccurring();

        int currentStatementIndex = -1;
        boolean flag = false;

        for (int i = 0; i < lines.size(); i++) {
            words = Arrays.stream(lines.get(i).getLine().split(" "))
                    .map(el -> el.replaceAll("\\W", "")).map(String::toLowerCase).collect(Collectors.toList());
            for (int j = 0; j < words.size(); j++) {

                for (int l = currentStatementIndex == -1 ? 0 : currentStatementIndex; l < SearchStatement.SEARCHED_STATEMENT.length; l++) {
                    if (SearchStatement.SEARCHED_STATEMENT[l].equals(words.get(j))) {
                        wordOccurring.setLine(lines.get(i).getNumberOfLine());
                        wordOccurring.addPercentage(SearchStatement.PERCENTAGES[l]);
                        flag = true;
                        if (j + 1 >= words.size()) {
                            currentStatementIndex = l + 1;
                            flag = true;
                            break;
                        } else {
                            flag = false;
                            j++;
                        }
                    } else {
                        flag = false;
                        if (currentStatementIndex != -1) {
                            occurring.add(wordOccurring);
                            wordOccurring = new WordOccurring();
                            currentStatementIndex = -1;
                        }
                    }
                }
                if (currentStatementIndex != -1 && flag) {
                    occurring.add(wordOccurring);
                    wordOccurring = new WordOccurring();
                    currentStatementIndex = -1;
                }
            }
            flag = false;
        }
        return occurring;
    }
}
