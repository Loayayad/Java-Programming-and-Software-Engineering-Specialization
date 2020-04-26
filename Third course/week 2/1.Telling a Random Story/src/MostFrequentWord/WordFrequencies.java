package MostFrequentWord;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {

        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();

        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);

            }
        }
    }

    public void tester() {
        findUnique();
        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        System.out.println("# unique words: " + myWords.size());
        findIndexOfMax();
    }

    public void findIndexOfMax() {

        String maxWord = null;
        int maxNum = 0;

        for (int k = 0; k < myWords.size(); k++) {

            if (myFreqs.get(k) > maxNum) {
                maxWord = myWords.get(k);
                maxNum = myFreqs.get(k);
            }

        }
        System.out.println("The word that occurs most often and its count are: " + maxWord + " " + maxNum);

    }
}