package breakCaesarCipher;

import java.util.Random;
import edu.duke.*;

public class draft {

    public void simpleSimulate(int rolls) {
        Random rand = new Random();
        int twos = 0;
        int twelves = 0;

        for (int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            if (d1 + d2 == 2) {
                twos += 1;
            } else if (d1 + d2 == 12) {
                twelves += 1;
            }
        }
        System.out.println("2's=\t" + twos + "\t" + 100.0 * twos / rolls);
        System.out.println("12's=\t" + twelves + "\t" + 100.0 * twelves / rolls);
    }

    public void simulate(int rolls) {
        Random rand = new Random();
        int[] counts = new int[13];

        for (int k = 0; k < rolls; k++) {

            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts[d1 + d2] += 1;
        }
        for (int k = 2; k <= 12; k++) {
            System.out.println(k + "'s=\t" + counts[k] + "\t" + 100.0 * counts[k] / rolls);
        }
    }

    public int indexOf(String[] list, String word) {
        for (int k = 0; k < list.length; k++) {
            if (list[k].equals(word)) {
                return k;
            }
        }

        return -1;
    }


    public void countWords(FileResource resource, String[] common, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = indexOf(common, word);
            if (index != -1) {
                counts[index] += 1;
            }
        }
    }

    public String[] getCommon() {
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s : resource.words()) {
            common[index] = s;
            index += 1;
        }
        return common;
    }

    void countShakespeare() {
        String[] plays = { "caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt" };

        String[] common = getCommon();
        int[] counts = new int[common.length];
        for (int k = 0; k < plays.length; k++) {

            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource, common, counts);
            System.out.println("done with " + plays[k]);
        }

        for (int k = 0; k < common.length; k++) {
            System.out.println(common[k] + "\t" + counts[k]);
        }

    }
}