package breakCaesarCipher;

import edu.duke.*;

public class CaesarBreaker1 {

    public int[] countLetters(String s) {

        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];

        for (int k = 0; k < s.length(); k++) {
            char ch = s.charAt(k);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                counters[index] += 1;
            }
        }
        /*
         * for (int k = 0; k < counters.length; k++) {
         * System.out.println(alpha.charAt(k) + "\t" + counters[k]); }
         */

        return counters;
    }

    public int maxIndex(int[] values) {
        int max = 0;
        int index = 0;

        for (int i = 0; i < values.length; i++) {

            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }

        // System.out.println(index);
        return index;

    }

    public String halfOfString(String message, int start) {
        String answer = "";
        for (int i = start; i < message.length(); i += 2) {
            answer = answer + message.charAt(i);
        }
        return answer;
    }

    public int getKey(String encrypted) {

        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public void testDecrypt() {

        FileResource fr = new FileResource();
        String message = fr.asString();
        String decrypted = decrypt(message);
        System.out.println(decrypted);
    }

    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String messageF = halfOfString(encrypted, 0);
        String messageS = halfOfString(encrypted, 1);
        int key1 = getKey(messageF);
        int key2 = getKey(messageS);
        System.out.println(key1 + " " + key2);
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    public void testDecryptTwoKeys() {
        System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
    }

}