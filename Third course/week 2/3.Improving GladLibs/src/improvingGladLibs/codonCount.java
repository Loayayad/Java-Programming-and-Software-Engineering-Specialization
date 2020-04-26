package improvingGladLibs;

import java.util.HashMap;

import edu.duke.FileResource;

public class codonCount {

    private HashMap<String, Integer> map;

    public codonCount() {
        map = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        map.clear();
        int first = start;
        int last = start + 3;
        int size = dna.length()-start-1;

        while (size > 2) {
            String codon = dna.substring(first, last).toUpperCase();

            if (!map.containsKey(codon)) {
                map.put(codon, 1);
            } else {
                map.put(codon, map.get(codon) + 1);
            }

            first = last;
            last = first + 3;
            if (last > dna.length()) {
                break;
            }

            size = size - 3;
        }
        System.out.println("Reading frame starting with " + start + " results in " + map.size() + " unique codons");
        /*
         * for (String w : map.keySet()) { int value = map.get(w);
         * System.out.println(value + "\t" + w); }
         */

    }

    public String getMostCommonCodon() {
        int max = 0;
        String maxCodon = null;
        for (String w : map.keySet()) {
            int value = map.get(w);
            if (value > max) {
                max = value;
                maxCodon = w;
            }
        }
        // System.out.println(max + "\t" + maxCodon);
        return maxCodon;
    }

    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");

        for (String w : map.keySet()) {
            int value = map.get(w);
            if (value >= start && value <= end) {
                System.out.println(value + "\t" + w);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource("smalldna.txt");
        String dna = fr.asString();
        dna.trim();
        
        buildCodonMap(0, dna);
        String mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommon + " with count " + map.get(mostCommon));
        printCodonCounts(7, 7);

       /* buildCodonMap(1, dna);
        String mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommon + " with count " + map.get(mostCommon));
        printCodonCounts(1, 5);
*/
      /*  buildCodonMap(2, dna);
        String mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommon + " with count " + map.get(mostCommon));
        printCodonCounts(1, 5);
*/
    }
}