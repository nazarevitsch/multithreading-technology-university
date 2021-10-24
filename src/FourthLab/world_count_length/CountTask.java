package FourthLab.world_count_length;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class CountTask extends RecursiveTask<Integer> {

    private static final int wordsA = 10;
    private List<String> words;

    public CountTask(List<String> words) {
        this.words = words;
    }

    @Override
    protected Integer compute() {
        if (words.size() > wordsA) {
            return CountTask.invokeAll(createSubTasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(words);
        }
    }

    private Collection<CountTask> createSubTasks() {
        List<CountTask> dividedTasks = new ArrayList<>();

        dividedTasks.add(new CountTask(words.subList(0, words.size() / 2)));
        dividedTasks.add(new CountTask(words.subList(words.size() / 2, words.size())));

        return dividedTasks;
    }

    private Integer processing(List<String> words) {
        AtomicInteger sum = new AtomicInteger();
        words.stream().forEach(word -> sum.addAndGet(word.length()));
        return sum.get();
    }
}
