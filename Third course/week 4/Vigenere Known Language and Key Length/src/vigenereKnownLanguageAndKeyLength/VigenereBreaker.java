package vigenereKnownLanguageAndKeyLength;

import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i = i + totalSlices) {
            char c = message.charAt(i);
            answer.append(c);
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] slicedLetters = new String[klength];
        CaesarCracker c1 = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            slicedLetters[i] = sliceString(encrypted, i, klength);
            key[i] = c1.getKey(slicedLetters[i]);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<>();
        for (String word : fr.lines()) {
            words.add(word.toLowerCase());
        }
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W");
        int count = 0;
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count = count + 1;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        VigenereCipher v1;
        int max = 0;
        int keylength = 0;
        String decrypt = null;
        char mostCommon = mostCommonCharin(dictionary);
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            v1 = new VigenereCipher(key);
            String dec = v1.decrypt(encrypted);
            int count = countWords(dec, dictionary);
            if (count > max) {
                max = count;
                keylength = i;
                decrypt = dec;
            }
        }
        System.out.println("key length used to encrypt is " + keylength);
        return decrypt;
    }

    public char mostCommonCharin(HashSet<String> dictionary) {
        HashMap<Character, Integer> count = new HashMap<>();
        int max = 0;
        char mostCommon = 'a';
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                if (count.containsKey(word.charAt(i))) {
                    count.put(word.charAt(i), count.get(word.charAt(i)) + 1);
                } else {
                    count.put(word.charAt(i), 1);
                }
            }
        }
        for (Character character : count.keySet()) {
            if (count.get(character) > max) {
                max = count.get(character);
                mostCommon = character;
            }
        }
        System.out.println("the most common is " + mostCommon);
        return mostCommon;

    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String decrypt = null;
        String language = null;
        for (String languageName : languages.keySet()) {
            System.out.println("the language is " + languageName);
            String dec = breakForLanguage(encrypted, languages.get(languageName));
            int count = countWords(dec, languages.get(languageName));
            if (count > max) {
                max = count;
                decrypt = dec;
                language = languageName;
            }
        }
        System.out.println("The language of the text is " + language);
        System.out.println(decrypt.substring(0, 300));
    }

    /*
     * public void breakVigenere() { FileResource file = new
     * FileResource("messages/" + "secretmessage2" + ".txt"); // FileResource file =
     * new FileResource("messages/" + "draft" + ".txt"); String source =
     * file.asString(); FileResource dic = new FileResource("dictionaries/" +
     * "English"); HashSet<String> dictionary = readDictionary(dic);
     * 
     * String decrypt = breakForLanguage(source, dictionary);
     * 
     * // int[] keys = tryKeyLength(source, 38, 'e'); // VigenereCipher v1 = new
     * VigenereCipher(keys); // String dec = v1.decrypt(source);
     * 
     * System.out.println("Valid words are in the decrypted String is " +
     * countWords(decrypt, dictionary)); System.out.println(decrypt.substring(0,
     * 1000));
     * 
     * }
     */
    public void breakVigenere() {
        FileResource file = new FileResource("messages/" + "secretmessage4" + ".txt");
        String source = file.asString();
        DirectoryResource dictionary = new DirectoryResource();
        HashMap<String, HashSet<String>> dictionaryMap = new HashMap<>();
        for (File f : dictionary.selectedFiles()) {
            FileResource dictFile = new FileResource(f);
            HashSet<String> dictWords = readDictionary(dictFile);
            dictionaryMap.put(f.getName(), dictWords);
        }
        breakForAllLangs(source, dictionaryMap);
    }
}