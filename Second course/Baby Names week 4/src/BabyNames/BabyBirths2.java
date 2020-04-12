package BabyNames;

import edu.duke.*;

import java.io.*;

import org.apache.commons.csv.*;

public class BabyBirths2 {

    public int yearOfHighestRank(String name, String gender) {

        int maxRank = Integer.MAX_VALUE;
        int maxYear = -1;

        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {

            BabyBirths1 b1 = new BabyBirths1();
            int year = Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));
            int rank = b1.getRank(year, name, gender);

            if (rank != -1 && rank < maxRank ) {
                maxRank = rank;
                maxYear = year;
            }

        }
        return maxYear;

    }

    public void testYearOfHighestRank() {

        int max = yearOfHighestRank("Mich", "M");
        System.out.println(max);
    }

    public double getAverageRank(String name, String gender) {

        double averageRank = 0;
        double count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {

            BabyBirths1 b1 = new BabyBirths1();
            int year = Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));
            int rank = b1.getRank(year, name, gender);
            if (rank != -1) {
                averageRank += rank;
                
            } else {
                return -1.0;
            }
            count += 1;
        }
        return averageRank / count;
    }

    public void testGetAverageRank() {
        double avg = getAverageRank("Susan", "F");
        System.out.println(avg);
    }

    public int getBithsOfName(int year, String name, String gender) {

        int totalBirths = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");

        for (CSVRecord rec : fr.getCSVParser(false)) {

            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {

                totalBirths = Integer.parseInt(rec.get(2));
                break;
            }
        }
        return totalBirths;

    }

    public void testGetBirthsOfName() {
        int birthsNum = getBithsOfName(2012, "Emma", "F");
        System.out.println(birthsNum);
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {

        int totalBirths = 0;
        BabyBirths1 b1 = new BabyBirths1();
        int rank = b1.getRank(year, name, gender);

        for (int i = 1; i < rank; i++) {

            String higherName = b1.getName(year, i, gender);
            totalBirths += getBithsOfName(year, higherName, gender);

        }
        return totalBirths;

    }

    public void testGetTotalBirthsRankedHigher(){
        int totalBirths = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(totalBirths);
    }
}