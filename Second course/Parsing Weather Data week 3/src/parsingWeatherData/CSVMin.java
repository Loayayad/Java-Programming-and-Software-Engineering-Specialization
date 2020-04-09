package parsingWeatherData;

import edu.duke.*;

import java.io.*;

import org.apache.commons.csv.*;

public class CSVMin {

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {

        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {

            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));

            if (currentTemp < largestTemp && currentTemp != -9999) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestHumidity) {

        if (lowestHumidity == null) {
            lowestHumidity = currentRow;
        } else {
            if (currentRow.get("Humidity") != "N/A" && lowestHumidity.get("Humidity") != "N/A") {
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowestHumidity.get("Humidity"));

                if (currentTemp < lowestHum) {
                    lowestHumidity = currentRow;
                }
            }
        }

        return lowestHumidity;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord smallestSoFar = null;

        for (CSVRecord currentRow : parser) {

            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public void testColdestInDay() {

        FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-02.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Tempreture was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST"));
    }

    public String fileWithColdestTemperature() {

        File coldestFile = null;
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {

            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());

            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                coldestFile = f;
            } else {

                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));

                if (currentTemp < largestTemp && currentTemp != -9999) {
                    smallestSoFar = currentRow;
                    coldestFile = f;
                }
            }

        }

        return coldestFile.getAbsolutePath();
    }

    public void testFileWithColdestTemperature() {

        String fileWithColdestTemp = fileWithColdestTemperature();
        // System.out.println("Coldest day was in file " + fileWithColdestTemp);
        File f = new File(fileWithColdestTemp);
        String fileName = f.getName();

        System.out.println("Coldest day was in file " + fileName);
        // open the file and get the resources inside it
        FileResource fr = new FileResource(f);
        // make this resources in a form of parser to loop on them or send them to
        // another function
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestTemp = coldestHourInFile(parser);

        System.out.println("Coldest Temperature is: " + lowestTemp.get("TemperatureF"));

        System.out.println("All the Temperatures on the coldest day were");

        CSVParser parser2 = fr.getCSVParser();

        for (CSVRecord record : parser2) {

            double temp = Double.parseDouble(record.get("TemperatureF"));
            System.out.println(record.get("DateUTC") + " " + temp);
        }

    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord lowestHumidity = null;

        for (CSVRecord currentRow : parser) {

            lowestHumidity = getLowestOfTwo(currentRow, lowestHumidity);
        }

        return lowestHumidity;

    }

    public void testLowestHumidityInFile() {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {

        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);

            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);

        }
        return lowestSoFar;

    }

    public void testLowestHumidityInManyFiles() {

        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {

        double average = 0;
        double num = 0;
        double sum = 0;

        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += currentTemp;
            num++;
        }
        average = sum / num;
        return average;

    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {

        double sum = 0;
        double num = 0;

        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            if (currentHum >= value) {
                sum += currentTemp;
                num++;
            }
        }
        double average = sum / num;
        return average;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);

        if (!Double.isNaN(average)) {
            System.out.println("average temperature is " + average);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }
}