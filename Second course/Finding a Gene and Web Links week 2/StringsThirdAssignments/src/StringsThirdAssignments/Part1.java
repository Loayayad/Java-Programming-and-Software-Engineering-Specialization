package StringsThirdAssignments;

import edu.duke.*;

public class Part1 {

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

    public void testPrintAllGenes() {

        String dna = "AATGCTAGGGTAATATGCGATAGGT";
        printAllGenes(dna);
    }

    public StorageResource getAllGenes(String dna) {

        StorageResource geneList = new StorageResource();
        int startIndex = 0;

        while (true) {

            String currentGene = findGene(dna, startIndex);
            
            if (currentGene.isEmpty()) {
                break;
            }

            geneList.add(currentGene);

            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();

        }
        return geneList;
    }

    public void testGetAllGenes() {

        String dna = "AATGCTAGGGTAATATGCGATAGGT";

        System.out.println("Testing getAllGenes on");

        StorageResource genes = getAllGenes(dna);

        for (String g : genes.data()) {
            System.out.println(g);
        }
    }

    public double cgRatio(String dna) {

        double count = 0;

        for (int i = 0; i < dna.length(); i++) {
            char x;
            x = dna.charAt(i);
            if (x == 'C' || x == 'G') {

                count = count + 1;
                // System.out.println(x);
            }

        }
        double cgRatio = count / dna.length();
        return cgRatio;

    }

    public void testCgRatio() {

        String dna = "AATGCTAGGGTAATATGCGATAGGT";

        System.out.println("Testing getAllGenes on");
        System.out.println(cgRatio(dna));

    }

    public void processGenes(StorageResource sr) {
        StorageResource sr1 = new StorageResource();
        StorageResource sr2 = new StorageResource();

        System.out.println("genes sizes : " +sr.size());
        int max = 0;
        String longest = "";
        for (String g : sr.data()) {

            if (g.length() > 60) {
                // System.out.println("Strings longer than 9 characters : " + g + " " +
                // g.length());
                // break;
                sr1.add(g);
            }

            if (cgRatio(g) > 0.35) {
                // System.out.println("Strings cgRatio higher than 0.35 : " + g + " " +
                // g.length());
                sr2.add(g);
                // break;
            }

            if (g.length() > max) {
                max = g.length();
                longest = g;
            }

        }
        for (String x1 : sr1.data()) {

            System.out.println("Strings longer than 9 characters : " + x1 + " " + x1.length());
        }
        System.out.println("Strings size than 9 characters : " + sr1.size());

        for (String x2 : sr2.data()) {
            System.out.println("Strings cgRatio higher than 0.35 : " + x2 + " " + x2.length());
        }
        System.out.println("Strings size cgRatio higher than 0.35 : " + sr2.size());

        System.out.println("the longest gene: " + longest+ " "+ longest.length());

    }

    public void testProcessGenes() {
        /*
        StorageResource lo = new StorageResource();

        String s1 = "AATGCTAGGGTAATATGGT";
        processGenes(getAllGenes(s1));
        
        String s2 = "AAATGCGATAGGGTAATATT";
        processGenes(getAllGenes(s2));

        String s3 = "ATGTGGTGAATATGGT";
        processGenes(getAllGenes(s3));
        String s4 = "AATGCTAACTAGCTGACTAAT";
        processGenes(getAllGenes(s4));
        */
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();

        //printAllGenes(dna);
        processGenes(getAllGenes(dna));
        //lo.add(dna);
        //processGenes(lo);

    }

}