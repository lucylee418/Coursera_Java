import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class WeatherCSV {

    // public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
    //     if (largestSoFar == null) {
    //         largestSoFar = currentRow;
    //     }
    //     else {
    //         double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
    //         double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

    //         if (largestTemp < currentTemp) {
    //             largestSoFar = currentRow;
    //         }
    //     }
    //     return largestSoFar;
    // }


    // public CSVRecord hottestHourInFile(CSVParser parser) {
    //     CSVRecord largestSoFar = null;
        
    //     for (CSVRecord currentRow : parser) {
    //         largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
    //     }
    //     return largestSoFar;
    // }


    // public void testHottestInDay() {
    //     FileResource fr = new FileResource();
    //     CSVRecord largest = hottestHourInFile(fr.getCSVParser());
    //     System.out.println("hottest temperateur was "+largest.get("TemperatureF")+" at " + largest.get("TimeEST"));
    // }


    // public CSVRecord hottestInManyDays() {
    //     CSVRecord largestSoFar = null;

    //     DirectoryResource dr = new DirectoryResource();
    //     for (File f : dr.selectedFiles()) {
    //         FileResource fr = new FileResource(f);
    //         CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
    //         largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
    //     }
    //     return largestSoFar;
    // }


    // public void testHottestInManyDays() {
    //     CSVRecord largest = hottestInManyDays();
    //     System.out.println("hottest temperateur was "+largest.get("TemperatureF")+" at " + largest.get("DateUTC"));
    // }


    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord SmallestSoFar) {
        if (SmallestSoFar == null) {
            SmallestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double SmallestTemp = Double.parseDouble(SmallestSoFar.get("TemperatureF"));

            if ((SmallestTemp > currentTemp) && (currentTemp != -9999)) {
                SmallestSoFar = currentRow;
            }
        }
        return SmallestSoFar;
    }


    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord SmallestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
            SmallestSoFar = getSmallestOfTwo(currentRow, SmallestSoFar);
        }
        return SmallestSoFar;
    }


    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("colest temperateur was "+smallest.get("TemperatureF")+" at " + smallest.get("DateUTC"));
    }


    public CSVRecord fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }


    public void testFileWithColdestTemperature() {
        CSVRecord smallest = fileWithColdestTemperature();
        System.out.println("coldest temperateur was "+smallest.get("TemperatureF")+" at " + smallest.get("DateUTC"));
    }


    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        else {
            if(!(currentRow.get("Humidity").equals("N/A"))) {
                double currenthum = Double.parseDouble(currentRow.get("Humidity"));
                double lowesthum = Double.parseDouble(lowestSoFar.get("Humidity"));
                
                if (lowesthum > currenthum) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }


    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("lowest humidity was "+lowest.get("Humidity")+" at " + lowest.get("DateUTC"));
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
        System.out.println("lowest humidity was "+lowest.get("Humidity")+" at " + lowest.get("DateUTC"));

    }


    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0.0;
        int totalCount = 0;
        
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            totalTemp = totalTemp + currentTemp;
            totalCount ++;
        }
        return totalTemp / totalCount;
    }


    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgtemp = averageTemperatureInFile(parser);
        System.out.println("avergate temperature in file is "+ avgtemp);
    }


    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0.0;
        int totalCount = 0;

        for (CSVRecord currentRow : parser) {
            int currentHum = Integer.parseInt(currentRow.get("Humidity"));
            if (currentHum >= value) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                totalTemp = totalTemp + currentTemp;
                totalCount ++;
            }
        }
        if (totalCount == 0) {
            return 0.0;
        }
        return totalTemp / totalCount;
    }


    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgtemp = averageTemperatureWithHighHumidityInFile(parser, 80);

        if (avgtemp == 0.0) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("avergate temperature with that humidity is "+ avgtemp);
        }
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        WeatherCSV pr = new WeatherCSV();
        // pr.testColdestHourInFile();
        // pr.testFileWithColdestTemperature();
        // pr.testLowestHumidityInFile();
        // pr.testLowestHumidityInManyFiles();
        // pr.testAverageTemperatureInFile();
        pr.testAverageTemperatureWithHighHumidityInFile();
    }



}
