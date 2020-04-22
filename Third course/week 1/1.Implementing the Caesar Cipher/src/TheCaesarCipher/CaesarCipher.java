package TheCaesarCipher;

import edu.duke.*;

public class CaesarCipher {

    public String encrypt(String input, int key) {

        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++) {

            char currChar = encrypted.charAt(i);

            if (Character.isLowerCase(currChar)) {

                currChar = Character.toUpperCase(currChar);
                int idx = alphabet.indexOf(currChar);

                if (idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);

                }
            } else {
                int idx = alphabet.indexOf(currChar);

                if (idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);

                }
            }
        }
        return encrypted.toString();
    }

    public void testCaesar() {

        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);

    }

    public String encryptTwoKeys(String input, int key1, int key2) {

        String result = "";

        for (int i = 0; i < input.length(); i++) {

            char currChar = input.charAt(i);
            if (i % 2 == 0 && Character.isLetter(currChar)) {
                result += encrypt(Character.toString(currChar), key1);
            } else if (i % 2 != 0 && Character.isLetter(currChar)) {
                result += encrypt(Character.toString(currChar), key2);
            } else {
                result += currChar;
            }
        }
        return result;
    }

    public void testEncryptTwoKeys() {

        int key1 = 21;
        int key2 = 8;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        // String decrypted = encrypt(encrypted, 26 - key);
        // System.out.println(decrypted);

    }
}