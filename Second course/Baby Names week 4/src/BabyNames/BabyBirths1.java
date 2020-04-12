package BabyNames;

import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths1 {

    public void totalBirths(FileResource fr) {

        //int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {

            //int numBorn = Integer.parseInt(rec.get(2));
            //totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                //totalBoys += numBorn;
                totalBoys +=1;
            } else {
                //totalGirls += numBorn;
                totalGirls +=1;
            }

        }
        System.out.println("total births = " + totalBoys+totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender) {

        int femaleRank = 0;
        int maleRank = 0;
        int rank = -1;
        // FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" +
        // year + "short.csv");
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");

        for (CSVRecord rec : fr.getCSVParser(false)) {

            if (rec.get(1).equals("F")) {
                femaleRank += 1;
                if (rec.get(0).equals(name) && gender.equals("F")) {
                    rank = femaleRank;
                    break;
                }

            } else if (rec.get(1).equals("M")) {
                maleRank += 1;
                if (rec.get(0).equals(name) && gender.equals("M")) {
                    rank = maleRank;
                    break;
                }
            }
        }
        return rank;
    }

    public void testGetRank() {

        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }

    public String getName(int year, int rank, String gender) {

        String Name = "NO NAME";
        int femaleRank = 0;
        int maleRank = 0;
        // FileResource fr = new
        // FileResource("us_babynames/us_babynames_test/example-small.csv");
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");

        for (CSVRecord rec : fr.getCSVParser(false)) {

            if (rec.get(1).equals("F")) {
                femaleRank += 1;
                if (femaleRank == rank && rec.get(1).equals(gender)) {
                    Name = rec.get(0);
                    break;
                }

            } else if (rec.get(1).equals("M")) {
                maleRank += 1;
                if (maleRank == rank && rec.get(1).equals(gender)) {
                    Name = rec.get(0);
                    break;
                }
            }

        }
        return Name;
    }

    public void testGetName() {

        String name = getName(1982, 450, "M");
        System.out.println(name);
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {

        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " Would be " + newName + " if she was born in " + newYear);
    }

    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }

}