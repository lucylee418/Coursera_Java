import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyNames {

    // public void readOneFile(int year) {
    //     String fname = "data/yob" + year + ".txt";
    //     FileResource fr = new FileResource(fname);
    //     CSVParser parser = fr.getCSVParser(false);
    //     for (CSVRecord rec : parser) {
    //         String name = rec.get(0);
    //         String gender = rec.get(1);
    //     }
    // }


    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String name = rec.get(0);
            String gender = rec.get(1);
            String numborn = rec.get(2);
            
            System.out.println("Name: " + name + ", Gender: "+ gender+", Num Born: "+numborn);
        }
    }
    

    public static void main (String[] args) {
        System.out.println("Start!");
        BabyNames pr = new BabyNames();
        pr.printNames();
    }

}
