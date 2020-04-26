package MostFrequentWord;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public CharactersInPlay() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void update(String person) {

        int index = myWords.indexOf(person);
        if (index == -1) {
            myWords.add(person);
            myFreqs.add(1);
        } else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value + 1);

        }

    }

    public void findAllCharacters() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();

        for (String s : resource.lines()) {

            if (s.contains(".")) {

                String person = s.substring(0, s.indexOf("."));
                // System.out.println(person);
                update(person);
            }
        }
    }

    public void tester() {
        findAllCharacters();

        for (int k = 0; k < myWords.size(); k++) {
            if (myFreqs.get(k) > 5) {
                System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
            }
        }

        charactersWithNumParts(10, 15);
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

    public void charactersWithNumParts(int num1, int num2) {

        for (int k = 0; k < myWords.size(); k++) {
            if (myFreqs.get(k) >= num1 && myFreqs.get(k) <= num2) {
                System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
            }
        }
    }

}