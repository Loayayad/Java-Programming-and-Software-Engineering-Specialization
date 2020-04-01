package StringsSecondAssignments;

class Part1 {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {

        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(startIndex, currIndex + 1);
            }
        }
        return dnaStr.length();
    }

    public void testFindStopCodon() {

        String dna = "AATGCTAGGGTAATATGGT";
        int startIndex = dna.indexOf("ATG");
        System.out.println("DNA strand is " + dna);
        int gene = findStopCodon(dna, startIndex, "TAA");
        System.out.println("Gene strand is " + gene);

        dna = "AAATGCTAGGGTAATATT";
        startIndex = dna.indexOf("ATG");
        System.out.println("DNA strand is " + dna);
        gene = findStopCodon(dna, startIndex, "TAG");
        System.out.println("Gene strand is " + gene);
    }

    public String findGene(String dna, int where) {

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);

    }

    public void testFindGene() {

        String dna = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna, 0);
        System.out.println("Gene strand is " + gene);

        dna = "AAATGCGATAGGGTAATATT";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene strand is " + gene);

        dna = "ATGTGGTGAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene strand is " + gene);

        dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene strand is " + gene);

    }

    public void printAllGenes(String dna) {

        int startIndex = 0;

        while (true) {

            String currentGene = findGene(dna, startIndex);

            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }

    }

    public void testPrintAllGenes(){

        String dna = "AATGCTAGGGTAATATGCGATAGGT";
        printAllGenes(dna);
    }

}