package FourthLab.calculate_statistic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MiddleCurvatureDeviationTask extends RecursiveTask<Double> {

    private List<String> words;
    private double middleLettersPerWord;

    public MiddleCurvatureDeviationTask(List<String> words, double middleLettersPerWord) {
        this.words = words;
        this.middleLettersPerWord = middleLettersPerWord;
    }

    @Override
    protected Double compute() {
        if (words.size() > Statistic.WORDS_PER_TASK) {
            return CountLetterTask.invokeAll(createSubTasks())
                    .stream()
                    .mapToDouble(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(words);
        }
    }

    private Collection<MiddleCurvatureDeviationTask> createSubTasks() {
        List<MiddleCurvatureDeviationTask> dividedTasks = new ArrayList<>();

        dividedTasks.add(new MiddleCurvatureDeviationTask(words.subList(0, words.size() / 2), middleLettersPerWord));
        dividedTasks.add(new MiddleCurvatureDeviationTask(words.subList(words.size() / 2, words.size()), middleLettersPerWord));

        return dividedTasks;
    }

    private Double processing(List<String> words) {
        return words.stream().mapToDouble( word -> Math.pow(word.length() - middleLettersPerWord, 2)).sum();
    }
}
