package FourthLab.search_same_words;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class SearchSameWordsTask extends RecursiveAction {

    private File currentFile;

    public SearchSameWordsTask(File currentFile) {
        this.currentFile = currentFile;
    }

    @Override
    protected void compute() {

    }

    private Collection<SearchSameWordsTask> createSubTasks() {
        List<SearchSameWordsTask> tasks = new ArrayList<>();

        return tasks;
    }

    private void calculate() {

    }
}
