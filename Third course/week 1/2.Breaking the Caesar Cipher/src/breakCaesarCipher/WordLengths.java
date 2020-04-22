package breakCaesarCipher;

import edu.duke.*;

public class WordLengths {

    public int[] countWordLendths(FileResource resource, int[] counts) {

        for (String word : resource.words()) {
            word = word.toLowerCase();

            /*
             * if (word.matches("^[a-zA-Z]*$")) { counts[word.length()] += 1; }
             */

            int l = word.length();
            if (!Character.isLetter(word.charAt(0))) {
                l--;
            }
            if (!Character.isLetter(word.charAt(word.length() - 1)) && word.length() != 1) {
                l--;
            }
            counts[l]++;

        }
        return counts;
    }

    public int indexOfMax(int[] values) {

        int index = 0;
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                index = i;

            }
        }
        return index;

    }

    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[31];

        counts = countWordLendths(resource, counts);
        for (int k = 1; k < counts.length; k++) {

            System.out.println(counts[k] + " words of length " + k);
        }
        int index = indexOfMax(counts);
        System.out.println(index);

    }

}