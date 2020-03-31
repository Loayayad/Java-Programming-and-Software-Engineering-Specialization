package StringsFirstAssignments;

class Part1 {

    public String findSimpleGene(String dna) {

        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {

            result = dna.substring(startIndex, stopIndex + 3);
            return result;
        }
        return "";

    }

    public void testSimpleGene() {

        String dna = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene strand is " + gene);

        dna = "AAATGCTAGGGTAATATT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene strand is " + gene);

        dna = "ATAGGGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene strand is " + gene);

    }
}
