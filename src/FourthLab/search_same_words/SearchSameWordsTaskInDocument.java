package FourthLab.search_same_words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SearchSameWordsTaskInDocument extends RecursiveTask<WordsOccurringTable> {

    private static final int LINES_PER_TASK = 1000;
    private List<Line> lines;
    private String currentFileName;

    public SearchSameWordsTaskInDocument(List<Line> lines, String currentFileName) {
        this.lines = lines;
        this.currentFileName = currentFileName;
    }

    @Override
    protected WordsOccurringTable compute() {
        if (lines.size() > LINES_PER_TASK) {
            List<SearchSameWordsTaskInDocument> list = new ArrayList<>(SearchSameWordsTaskInDocument.invokeAll(createSubTasks()));
            WordsOccurringTable table = list.get(0).join();
            for (int i = 1; i < list.size(); i++) {
                table.merge(list.get(i).join());
            }
            return table;
        } else {
            return calculate();
        }
    }

    private Collection<SearchSameWordsTaskInDocument> createSubTasks() {
        List<SearchSameWordsTaskInDocument> list = new ArrayList<>();
        list.add(new SearchSameWordsTaskInDocument(lines.subList(0, lines.size() / 2), currentFileName));
        list.add(new SearchSameWordsTaskInDocument(lines.subList(lines.size() / 2, lines.size()), currentFileName));
        return list;
    }

    private WordsOccurringTable calculate() {
        WordsOccurringTable table = new WordsOccurringTable();

        for (int i = 0; i < lines.size(); i++) {
            int finalI = i;
            Arrays.stream(lines.get(i).getLine().split(" ")).forEach(word -> {
                table.add(word.replaceAll("\\W", "").toLowerCase(), new Occurring(lines.get(finalI).getNumberOfLine(), currentFileName));
            });
        }

        return table;
    }
}
