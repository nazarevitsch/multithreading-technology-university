package FourthLab.calculate_statistic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class HistogramTask extends RecursiveTask<HistogramTable> {

    private static final int wordsPerTask = 30;
    private List<String> words;

    public HistogramTask(List<String> words) {
        this.words = words;
    }

    @Override
    protected HistogramTable compute() {
        if (words.size() > wordsPerTask) {
            HistogramTable table = new HistogramTable();
            CountLetterTask.invokeAll(createSubTasks())
                    .stream()
                    .map(ForkJoinTask::join).forEach(table::merge);
            return table;
        } else {
            return processing(words);
        }
    }

    private Collection<HistogramTask> createSubTasks() {
        List<HistogramTask> dividedTasks = new ArrayList<>();

        dividedTasks.add(new HistogramTask(words.subList(0, words.size() / 2)));
        dividedTasks.add(new HistogramTask(words.subList(words.size() / 2, words.size())));

        return dividedTasks;
    }

    private HistogramTable processing(List<String> words) {
        HistogramTable histogramTable = new HistogramTable();

        words.forEach(word -> {
            char[] letters = word.toLowerCase().toCharArray();
            for (int i = 0; i < letters.length; i++) {
                histogramTable.add(letters[i]);
            }
        });

        return histogramTable;
    }
}
