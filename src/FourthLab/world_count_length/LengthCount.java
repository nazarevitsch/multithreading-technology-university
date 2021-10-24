package FourthLab.world_count_length;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class LengthCount {
    private List<String> words;
    private ForkJoinPool forkJoinPool;

    public LengthCount(File file) {
        forkJoinPool = ForkJoinPool.commonPool();
        getDataFromFile(file);

        System.out.println("SIZE: " + words.size());

        CountTask countTask = new CountTask(words);
        System.out.println(forkJoinPool.invoke(countTask));
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
