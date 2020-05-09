package vigenereCipher;

import edu.duke.FileResource;

public class tester {

    public void testCaesarCipher() {
        FileResource file = new FileResource("test/" + "titus-small" + ".txt");
        String source = file.asString();
        char c = 'A';
        CaesarCipher c1 = new CaesarCipher(5);
        System.out.println(c1.encrypt(source));
        System.out.println(c1.decrypt(c1.encrypt(source)));
        System.out.println(c1.encryptLetter(c));
        System.out.println(c1.decryptLetter(c1.encryptLetter(c)));
    }

    public void testCaesarCracker() {
        FileResource file = new FileResource("test/" + "oslusiadas_key17" + ".txt");
        String source = file.asString();
        char c = 'a';
        CaesarCracker c2 = new CaesarCracker(c);
        System.out.println(c2.decrypt(source));
    }

    public void testVigenereCipher() {
        FileResource file = new FileResource("test/" + "titus-small" + ".txt");
        String source = file.asString();
        int[] key = { 17, 14, 12, 4 };
        VigenereCipher v1 = new VigenereCipher(key);
        System.out.println(v1.encrypt(source));
        System.out.println(v1.decrypt(v1.encrypt(source)));

    }

    public void testVigenereBreaker() {
        VigenereBreaker b1 = new VigenereBreaker();
        /*
         * System.out.println(b1.sliceString("abcdefghijklm", 0, 3));
         * System.out.println(b1.sliceString("abcdefghijklm", 1, 3));
         * System.out.println(b1.sliceString("abcdefghijklm", 0, 4));
         * System.out.println(b1.sliceString("abcdefghijklm", 4, 5));
         */
        FileResource file = new FileResource("messages/" + "secretmessage1" + ".txt");
        String source = file.asString();
        int[] keys = b1.tryKeyLength(source, 4, 'e');
        for (int key : keys) {
            System.out.println(key);
        }
    }

    public void testBreakVigenere() {
        VigenereBreaker v = new VigenereBreaker();
        v.breakVigenere();
    }
}