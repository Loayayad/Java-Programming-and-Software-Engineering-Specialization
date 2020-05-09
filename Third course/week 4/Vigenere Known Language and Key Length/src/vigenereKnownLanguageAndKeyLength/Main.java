package vigenereKnownLanguageAndKeyLength;

public class Main {
    public static void main(String[] args) throws Exception {
        // tester caesar = new tester();
        // caesar.testCaesarCipher();

        // tester caesarCrack = new tester();
        // caesarCrack.testCaesarCracker();

        // tester vigenereCipher = new tester();
        // vigenereCipher.testVigenereCipher();

        tester vigenereBreaker = new tester();
        //vigenereBreaker.testVigenereBreaker();
        vigenereBreaker.testBreakVigenere();
    }
}