import edu.duke.*;
import org.apache.commons.csv.*;

public class UsingCSV {

    public void readFood(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser) {
            System.out.println(record.get("Name"));
        }
    }

    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                
            }
        }
    }
}
