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
            int numBorn = Integer.parseInt(numborn);
    
            if (numBorn <= 100) {
                System.out.println("Name: " + name + ", Gender: "+ gender+", Num Born: "+numborn);
            }  
        }

    }


    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;

            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }

        System.out.println("total births: " + totalBirths);
        System.out.println("total boys: " + totalBoys);
        System.out.println("total girls: " + totalGirls);
    }


    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }



    public int getRank(FileResource fr, String name, String gender) {
        int nameRank = 0;
        String verify = "No";

        for (CSVRecord rec : fr.getCSVParser(false)) {
            // If gender matches with the row gender
            if (rec.get(1).equals(gender)){
                nameRank ++;
                // If name matches as well
                if (rec.get(0).equals(name)){
                    verify = "Yes";
                    break;
                }
            }
        }
        if (verify == "Yes") {
            return nameRank;
        }
        return -1;
    }
        

    public void testGetRank () {
        // Select file
        FileResource fr = new FileResource();
        // Select name and gender
        String name = "Ava";
        String gender = "M";
        int nameRank = getRank(fr, name, gender);
        System.out.println("Name: "+ name + ", Gender: "+gender+", Ranking: "+nameRank);
    }



    public String getName(FileResource fr, int rank, String gender) {
        String nthName = "";
        int nthRow = 0;
        int nameGirl = 0;
        int count = 1;

        // Get number of girls' names
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("F")) {
                nameGirl ++;
            }
        }
        System.out.println("Number of girl names is "+nameGirl);

        // Find the row number
        if (gender == "F") {
            nthRow = rank;
        }
        else {
            nthRow = nameGirl + rank;
        }
        System.out.println("We should look at "+nthRow+"th row");

        // Find nth row's name
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if ((count == nthRow) && (rec.get(1).equals(gender))) {
                nthName = rec.get(0);
                break;
            }
            System.out.println("Count is "+count);
            count ++;
        }

        if (nthName.isEmpty()){
            return "NO NAME";
        }
        return nthName;
    }
    

    public void testGetName () {
        // Select file
        FileResource fr = new FileResource();
        // Select name and gender
        int rank = 5;
        String gender = "M";
        String nthName = getName(fr, rank, gender);
        System.out.println(rank+"th "+ gender + " name is: "+nthName);
    }



    public static void main (String[] args) {
        System.out.println("Start!");
        BabyNames pr = new BabyNames();
        // pr.printNames();
        // pr.testTotalBirths();
        // pr.testGetRank();
        pr.testGetName();
    }




}
