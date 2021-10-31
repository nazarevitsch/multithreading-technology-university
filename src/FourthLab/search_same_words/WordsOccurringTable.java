package FourthLab.search_same_words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class WordsOccurringTable {

    private HashMap<String, List<Occurring>> map;

    public WordsOccurringTable() {
        this.map = new HashMap<>();
    }

    public void add(String word, Occurring occurring) {
        List<Occurring> occurringList = map.get(word);
        if (occurringList == null) {
            occurringList = new ArrayList<>();
            occurringList.add(occurring);
            map.put(word, occurringList);
        } else {
            occurringList.add(occurring);
        }
    }

    public void merge(WordsOccurringTable table) {
        table.map.keySet().forEach( word -> {
            List<Occurring> occurringList = this.map.get(word);
            if (occurringList == null) {
                occurringList = table.map.get(word);
                this.map.put(word, occurringList);
            } else {
                occurringList.addAll(table.map.get(word));
            }
        });
    }

    public List<Occurring> get(String key) {
        return this.map.get(key);
    }

    public Set<String> getKeys() {
        return map.keySet();
    }
}
