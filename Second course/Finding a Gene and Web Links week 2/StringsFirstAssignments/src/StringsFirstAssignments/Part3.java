package StringsFirstAssignments;

class Part3 {

    int count = 0;
    int index = 0;

    public boolean twoOccurrences(String stringa, String stringb) {

        int x = stringb.indexOf(stringa);
        if (x != -1) {
            count = count + 1;
            index = x + 1;
            stringb = stringb.substring(index);
            return twoOccurrences(stringa, stringb);

        }

        if (count > 1) {
            count = 0;
            return true;

        } else {
            count = 0;
            return false;
        }

    }

    public String lastPart(String stringa, String stringb) {

        int x = stringb.indexOf(stringa);
        if (x != -1) {

            index = stringa.length() + x;
            stringb = stringb.substring(index);
            return stringb;

        }

        return stringb;
    }

    public void testing() {

        /*boolean x = twoOccurrences("by", "A story by Abby Long");
        System.out.println(x);

        x = twoOccurrences("a", "banana");
        System.out.println(x);

        x = twoOccurrences("atg", "ctgtatgta");
        System.out.println(x);
        */
        String y = lastPart("an","monankobanana");
        System.out.println(y);

        y = lastPart("zoo","monanoka");
        System.out.println(y);

    }
}