package FourthLab.calculate_statistic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Statistic {

    public static final int WORDS_PER_TASK = 30000;

    private List<String> words;
    private ForkJoinPool forkJoinPool;

    public Statistic(File file) {
        forkJoinPool = ForkJoinPool.commonPool();
        getDataFromFile(file);
    }

    public void calculateOneThread() {
        long start = System.currentTimeMillis();

        System.out.println("Words: " + words.size());

        int lettersInText = words.stream().mapToInt(String::length).sum();
        double middleLettersPerWord = (double) lettersInText / words.size();

        System.out.println("Letters: " + lettersInText);
        System.out.println("Middle letters per each word: " + middleLettersPerWord);

        double deviation = Math.sqrt(words.stream().mapToDouble(el -> Math.pow(el.length() - middleLettersPerWord, 2)).sum() / words.size());

        System.out.println("Middle deviation: " + deviation);

        HistogramTable histogramTable = new HistogramTable();
        words.forEach(word -> {
            char[] letters = word.toLowerCase().toCharArray();
            for (int i = 0; i < letters.length; i++) {
                histogramTable.add(letters[i]);
            }
        });

        System.out.println(histogramTable);

        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;
        System.out.println("Time multi tasks: " + t + " sec.\n\n\n");
    }

    public void calculateMultiThreads() {
        long start = System.currentTimeMillis();

        System.out.println("Words: " + words.size());

        CountLetterTask countTask = new CountLetterTask(words);
        int lettersInText = forkJoinPool.invoke(countTask);
        double middleLettersPerWord = (double) lettersInText / words.size();

        System.out.println("Letters: " + lettersInText);
        System.out.println("Middle letters per each word: " + middleLettersPerWord);

        MiddleCurvatureDeviationTask middleCurvatureDeviationTask = new MiddleCurvatureDeviationTask(words, middleLettersPerWord);
        double deviation = Math.sqrt(forkJoinPool.invoke(middleCurvatureDeviationTask) / words.size());

        System.out.println("Middle deviation: " + deviation);

        HistogramTask histogramTask = new HistogramTask(words);
        HistogramTable histogramTable = forkJoinPool.invoke(histogramTask);

        System.out.println(histogramTable);

        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;
        System.out.println("Time multi tasks: " + t + " sec.\n\n\n");
    }

    private void getDataFromFile(File file) {
        this.words = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while (line != null) {
                words.addAll(Arrays.stream(line.split(" ")).map(world -> world.replaceAll("\\W", "")).collect(Collectors.toList()));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("No such file...");
            System.exit(0);
        }
    }
}
