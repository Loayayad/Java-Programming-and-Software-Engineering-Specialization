package improvingGladLibs;

import edu.duke.*;
import java.util.*;

public class GladLibsByHashMap {

    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords = new ArrayList<String>();;
    private ArrayList<String> considerWords = new ArrayList<String>();;

    private Random myRandom;

    // private static String dataSourceURL =
    // "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibsByHashMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibsByHashMap(String source) {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {

        String[] labels = { "adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit" };
        for (String s : labels) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));

        if (!considerWords.contains(w.substring(first + 1, last))) {
            considerWords.add(w.substring(first + 1, last));
        }

        while (usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first + 1, last));
        }
        usedWords.add(sub);
        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate(dataSourceDirectory + "/madtemplate3.txt");
        printOut(story, 60);
        System.out.println();
        for (int k = 0; k < usedWords.size(); k++) {
            System.out.println(usedWords.get(k));
        }
        System.out.println(usedWords.size());

        System.out.println("Number of words in all the ArrayLists in the HashMap : " + totalWordsInMap());
        System.out.println("the total number of words of the categories used in GladLib : " + totalWordsConsidered());

    }

    public int totalWordsInMap() {
        int total = 0;
        for (String w : myMap.keySet()) {
            total += myMap.get(w).size();
        }
        return total;
    }

    public int totalWordsConsidered() {
        int total = 0;
        for (String w : considerWords) {
            // System.out.println(w);
            total += myMap.get(w).size();
        }
        return total;
    }

}