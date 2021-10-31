package FourthLab.search_same_words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchSameWordsTaskInFolder extends RecursiveTask<WordsOccurringTable> {

    private File currentFile;
    public ForkJoinPool forkJoinPool;

    public SearchSameWordsTaskInFolder(File currentFile) {
        this.currentFile = currentFile;
    }

    @Override
    protected WordsOccurringTable compute() {
        if (currentFile.isDirectory()) {
            List<SearchSameWordsTaskInFolder> list = new ArrayList<>(SearchSameWordsTaskInFolder.invokeAll(createSubTasks()));
            WordsOccurringTable table = list.get(0).join();
            for (int i = 1; i < list.size(); i++) {
                table.merge(list.get(i).join());
            }
            return table;
        } else {
            return calculate();
        }
    }

    private Collection<SearchSameWordsTaskInFolder> createSubTasks() {
        List<SearchSameWordsTaskInFolder> tasks = new ArrayList<>();
        File[] files = currentFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            tasks.add(new SearchSameWordsTaskInFolder(files[i]));
        }
        return tasks;
    }

    private WordsOccurringTable calculate() {
        List<Line> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
            String line = reader.readLine();
            int numberOfLine = 1;

            while (line != null) {
                lines.add(new Line(line, numberOfLine++));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("No file on path: " + currentFile.getAbsolutePath());
            return new WordsOccurringTable();
        }
        this.forkJoinPool = ForkJoinPool.commonPool();

        return forkJoinPool.invoke(new SearchSameWordsTaskInDocument(lines, currentFile.getName()));
    }
}
