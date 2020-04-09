package CSV;

import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {

    public void tester() {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        //System.out.println(countryInfo(parser, "Nauru"));
        // parser = fr.getCSVParser();
        //listExportersTwoProducts(parser,"cotton","flowers");
        // parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser, "cocoa"));
        // parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");

    }

    public String countryInfo(CSVParser parser, String country) {

        String countryInformation = "";
        for (CSVRecord record : parser) {

            //String countries = record.get("Country");
            //if (countries.indexOf(country) !=-1)

            if (record.get("Country").equals(country)) {

                countryInformation = record.get("Country") + ": " + record.get("Exports") + ": "
                        + record.get("Value (dollars)");
                return countryInformation;
            }
        }
        countryInformation = "NOT FOUND";
        return countryInformation;
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {

        for (CSVRecord record : parser) {

            String exports = record.get("Exports");

            if (exports.indexOf(exportItem1) != -1 && exports.indexOf(exportItem2) != -1) {

                System.out.println(record.get("Country"));

            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {

        int count = 0;
        for (CSVRecord record : parser) {

            String exports = record.get("Exports");

            if (exports.indexOf(exportItem) != -1) {

                count++;

            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount) {

        for (CSVRecord record : parser) {

            String values = record.get("Value (dollars)");

            if (values.length() > amount.length()) {

                String countryValue = record.get("Country") + " " + record.get("Value (dollars)");
                System.out.println(countryValue);
            }
        }

    }

}