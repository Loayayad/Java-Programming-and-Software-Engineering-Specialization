package improvingGladLibs;

import java.io.File;
import java.util.*;
import edu.duke.*;

import edu.duke.FileResource;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> map;

    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {

        FileResource fr = new FileResource(f);
        for (String s : fr.words()) {

            if (this.map.containsKey(s)) {
                ArrayList<String> fileList = this.map.get(s);
                if (!fileList.contains(f.getName())) {
                    fileList.add(f.getName());
                    map.put(s, fileList);
                }
            } else {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(f.getName());
                map.put(s, fileList);
            }
        }

    }

    public void buildWordFileMap() {
        this.map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            this.addWordsFromFile(f);
        }

        /*
         * for (String w : map.keySet()) { System.out.print(w + " ");
         * System.out.println(); for (String s : map.get(w)) { System.out.print(s +
         * " "); } System.out.println(); }
         */
    }

    public int maxNumber() {
        int max = 0;
        String maxKey = null;

        for (String w : map.keySet()) {

            int value = map.get(w).size();
            if (value > max) {
                max = value;
                maxKey = w;
            }
        }
        System.out.println("Max key: " + maxKey + " appearing number " + max);
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {

        ArrayList<String> wordsInFiles = new ArrayList<String>();

        for (String w : map.keySet()) {
            int value = map.get(w).size();
            if (value == number) {
                wordsInFiles.add(w);
            }
        }
        return wordsInFiles;
    }

    public void printFilesIn(String word) {

        for (String s : map.get(word)) {
            System.out.println(s);
        }
    }

    public void tester() {
        buildWordFileMap();
        //int max = this.maxNumber();
        //ArrayList<String> words = this.wordsInNumFiles(4);
        /*for (String word : words) {
            System.out.println(word + " appears in the files ");
            this.printFilesIn(word);
            System.out.println();
        }
        */
        //System.out.println(words.size());
        System.out.println("tree" + " appears in the files ");
          this.printFilesIn("tree");
    }
}
