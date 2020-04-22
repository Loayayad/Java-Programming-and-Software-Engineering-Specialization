package TheCaesarCipher;

public class worldPlay {

    public boolean isVowel(char ch) {

        boolean result = false;
        String vowels = "AEIOUaeiou";

        if (vowels.indexOf(ch) != -1) {

            result = true;
        } else {

            result = false;
        }
        return result;
    }

    public void testIsVowel() {

        boolean result = isVowel('h');
        System.out.println(result);
        result = isVowel('O');
        System.out.println(result);
        result = isVowel('a');
        System.out.println(result);
    }

    public String replaceVowels(String phrase, char ch) {

        String result = "";

        for (int i = 0; i < phrase.length(); i++) {

            char currChar = phrase.charAt(i);
            if (isVowel(currChar)) {

                result += ch;
            } else {
                result += currChar;
            }
        }
        return result;
    }

    public void testReplaceVowels() {
        String result = replaceVowels("Hello World", '*');
        System.out.println(result);
    }

    public String emphasize(String phrase, char ch) {

        String result = "";

        for (int i = 0; i < phrase.length(); i++) {

            char currChar = phrase.charAt(i);
            if (currChar == ch && i % 2 == 0) {
                result += "*";
            } else if (currChar == ch && i % 2 != 0) {
                result += "+";
            } else {
                result += currChar;
            }
        }
        return result;
    }

    public void testEmphasize() {
        String result = emphasize("dna ctgaaactga", 'a');
        System.out.println(result);

        result = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(result);
    }
}
