package StringsSecondAssignments;

class Part2 {

    public int howMany(String stringa, String stringb, int count) {

        int index = 0;
        int x = stringb.indexOf(stringa);
        if (x != -1) {
            count = count + 1;
            index = x + stringa.length();
            stringb = stringb.substring(index);
            return howMany(stringa, stringb, count);

        }

        if (count > 1) {
            return count;

        } else {
            return count;
        }

    }

    public void testHowMany() {

        int count = 0;
        int x = howMany("by", "A story by Abby Long", count);
        System.out.println(x);

        x = howMany("GAA", "ATGAACGAATTGAATC", count);
        System.out.println(x);

        x = howMany("AA", "ATAAAA", count);
        System.out.println(x);

    }
}