package parsingWeatherData;

public class  parsingWeatherData{
    public static void main(String[] args) throws Exception {
        //CSVMax m1 = new CSVMax();
        //m1.testHottestInDay();
        //m1.testHottestInManyDay();

        CSVMin m2 = new CSVMin();
        //m2.testColdestInDay();
        m2.testFileWithColdestTemperature();
        //m2.testLowestHumidityInFile();
        //m2.testLowestHumidityInManyFiles();
        //m2.testAverageTemperatureInFile();
        //m2.testAverageTemperatureWithHighHumidityInFile();
    }
}