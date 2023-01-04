import edu.duke.*;
import org.apache.commons.csv.*;

public class UsingCSV {

    public void tester() {
        FileResource fr = new FileResource();

        CSVParser parser1 = fr.getCSVParser();
        System.out.println("Country info: "+countryInfo(parser1, "Nauru"));

        CSVParser parser2 = fr.getCSVParser();
        System.out.println("<fish and nuts>");
        listExportersTwoProducts(parser2, "fish", "nuts");

        CSVParser parser3 = fr.getCSVParser();
        System.out.println("<number of countries which export gold>");
        System.out.println(numberOfExporters(parser3, "gold"));

        CSVParser parser4 = fr.getCSVParser();
        bigExporters(parser4, "$999,999,999,999");
    }


    public String countryInfo(CSVParser parser, String country) {
        String countryDetails = "";
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                String countryName = record.get("Country");
                String exports = record.get("Exports");
                String usdvalue = record.get("Value (dollars)");
                countryDetails = countryName + ": " + exports + ": " + usdvalue;

                return countryDetails;
            }
        }
        return "NOT FOUND";
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
