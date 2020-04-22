package breakCaesarCipher;

public class breakCaesarCipher {
    public static void main(String[] args) throws Exception {

        //WordLengths w1 = new WordLengths();
        //w1.testCountWordLengths();

        // CaesarBreaker1 b1 = new CaesarBreaker1();
        // b1.countLetters("my name is loay");
        // b1.testDecrypt();
        // b1.testDecryptTwoKeys();

        //CaesarBreaker b2 = new CaesarBreaker();
        //b2.testDecrypt();

        CaesarCipher c1 = new CaesarCipher();
        // c1.testCaesar();
        c1.testEncryptTwoKeys();

    }
}