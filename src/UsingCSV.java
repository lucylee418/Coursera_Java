import edu.duke.*;
import org.apache.commons.csv.*;

public class UsingCSV {

    // public void readFood(){
    //     FileResource fr = new FileResource();
    //     CSVParser parser = fr.getCSVParser();
    //     for(CSVRecord record : parser) {
    //         System.out.println(record.get("Name"));
    //     }
    // }

    // public void listExporters(CSVParser parser, String exportOfInterest) {
    //     for (CSVRecord record : parser) {
    //         String export = record.get("Exports");
    //         if (export.contains(exportOfInterest)) {
    //             String country = record.get("Country");
    //             System.out.println(country);
    //         }
    //     }
    // }

    // public void whoExportsCoffee() {
    //     FileResource fr = new FileResource();
    //     CSVParser parser = fr.getCSVParser();
    //     listExporters(parser, "coffee");
    // }

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        countryInfo(parser, "Peru");
        listExportersTwoProducts(parser, "coffee", "gold");
        numberOfExporters(parser, "coffee");
        bigExporters(parser, "$999,999,999");
    }


    public String countryInfo(CSVParser parser, String country) {
        String countryDetails = "";
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            if (countryName.isEmpty()) {
                countryDetails = "NOT FOUND";
            }
            else {
                String exports = record.get("Exports");
                String usdvalue = record.get("Value (dollars)");
                countryDetails = countryName + ": " + exports + ": " + usdvalue;
            }
        }
        return countryDetails;
    }


    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && (exports.contains(exportItem2))) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }


    public int numberOfExporters(CSVParser parser, String exportItem) {
        int nums = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                nums ++;
            }
        }
        return nums;
    }


    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String usdvalue = record.get("Value (dollars)");
            if (usdvalue.length() > amount.length()) {
                String country = record.get("Country");
                System.out.print(country + " ");
                System.out.println(usdvalue);
            }
        }
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        UsingCSV pr = new UsingCSV();
        pr.tester();
    }
}
