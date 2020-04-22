package ObjectOrientedCaesar;

public class TestCaesarCipher {

    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
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

    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public void simpleTests() {

        /*
         * FileResource fr = new FileResource(); String message = fr.asString(); String
         * encrypted = encrypt(message, key);
         */

        String message = "i would love to go";
        CaesarCipher c1 = new CaesarCipher(18);
        String encrypted = c1.encrypt(message);
        System.out.println(encrypted);
        this.breakCaesarCipher(encrypted);
        // String decrypted = c1.decrypt(encrypted);
        // System.out.println(decrypted);

    }

    public void breakCaesarCipher(String input) {

        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        System.out.println(key);
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);

    }
}