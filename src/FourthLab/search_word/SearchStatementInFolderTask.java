package FourthLab.search_word;

import FourthLab.search_same_words.Line;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchStatementInFolderTask extends RecursiveTask<List<WordOccurring>> {

    private File currentFile;
    private ForkJoinPool forkJoinPool;

    public SearchStatementInFolderTask(File currentFile) {
        this.currentFile = currentFile;
    }

    @Override
    protected List<WordOccurring> compute() {
        if (currentFile.isDirectory()) {
            List<SearchStatementInFolderTask> list = new ArrayList<>(SearchStatementInFolderTask.invokeAll(createSubTasks()));
            List<WordOccurring> occurring = list.get(0).join();
            for (int i = 1; i < list.size(); i++) {
                occurring.addAll(list.get(i).join());
            }
            return occurring;
        } else {
            return calculate();
        }
    }

    private Collection<SearchStatementInFolderTask> createSubTasks() {
        List<SearchStatementInFolderTask> tasks = new ArrayList<>();
        File[] files = currentFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            tasks.add(new SearchStatementInFolderTask(files[i]));
        }
        return tasks;
    }

    private List<WordOccurring> calculate() {
        List<Line> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
            String line = reader.readLine();
            int numberOfLine = 1;

            while (line != null) {
                lines.add(new Line(line, numberOfLine++));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("EXCEPTION!!!  No file on path: " + currentFile.getAbsolutePath());
            return new ArrayList<WordOccurring>();
        }
        this.forkJoinPool = ForkJoinPool.commonPool();

        return forkJoinPool.invoke(new SearchStatementInDocumentTask(lines, currentFile.getName()));
    }
}
