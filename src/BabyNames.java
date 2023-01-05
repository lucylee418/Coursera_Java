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
        // Select file
        FileResource fr = new FileResource();
        // Look into every row
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String name = rec.get(0);
            String gender = rec.get(1);
            String numborn = rec.get(2);
            int numBorn = Integer.parseInt(numborn);
            // If number of names is less than 100
            if (numBorn <= 100) {
                System.out.println("Name: " + name + ", Gender: "+ gender+", Num Born: "+numborn);
            }  
        }

    }


    public void totalBirths (FileResource fr) {
        // Initialize
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        // For each row
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            // Number of boys
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            // Number of girls
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



    public int getRank(int year, String name, String gender) {
        // Selet file
        String fname = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        // String fname = "yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        // Initialize
        int nameRank = 0;
        String verify = "No";
        // For each row
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
        // Select year, name and gender
        int year = 2000;
        String name = "Ava";
        String gender = "F";
        int nameRank = getRank(year, name, gender);
        System.out.println("Name: "+ name + ", Gender: "+gender+", Ranking: "+nameRank);
    }



    public String getName(int year, int rank, String gender) {
        // Selet file
        String fname = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        // String fname = "yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        // Initialize
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
        // Find the row number
        if (gender == "F") {
            nthRow = rank;
        }
        else {
            nthRow = nameGirl + rank;
        }
        // Find nth row's name
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if ((count == nthRow) && (rec.get(1).equals(gender))) {
                nthName = rec.get(0);
                break;
            }
            count ++;
        }
        if (nthName.isEmpty()){
            return "NO NAME";
        }
        return nthName;
    }
    

    public void testGetName () {
        // Select year, rank and gender
        int year = 2000;
        int rank = 5;
        String gender = "F";
        String nthName = getName(year, rank, gender);
        System.out.println(rank+"th "+ gender + " name is: "+nthName);
    }


    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        // Determine the rank of name in the year they were born
        int rank = getRank(year, name, gender);
        //  print the name born in newYear that is at the same rank and same gender
        String newName = getName(newYear, rank, gender);
        System.out.println(year+" "+name+" -> "+newYear+" "+newName);
    }



    public static void main (String[] args) {
        System.out.println("Start!");
        BabyNames pr = new BabyNames();
        // pr.printNames();
        // pr.testTotalBirths();
        // pr.testGetRank();
        // pr.testGetName();
        pr.whatIsNameInYear("Lucy", 1992, 2014, "F");
    }




}
