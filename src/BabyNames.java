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
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int nameRank = getRank(year, name, gender);
        System.out.println("Name: "+ name + ", Gender: "+gender+", Ranking: "+nameRank);
    }



    public int numGirlNames(FileResource fr) {
        int numGirl = 0;
        // Get number of girls' names
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("F")) {
                numGirl ++;
            }
        }
        return numGirl;
    }


    public int numBoyNames(FileResource fr) {
        int numBoy = 0;
        // Get number of boys' names
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")) {
                numBoy ++;
            }
        }
        return numBoy;
    }


    public void testNumNames(int year, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        if (gender == "F"){
            System.out.println("Number of girl names in "+year+" is "+ numGirlNames(fr));
        }
        else{
            System.out.println("Number of boy names in "+year+" is "+ numBoyNames(fr));
        }
    }



    public String getName(int year, int rank, String gender) {
        // Selet file
        String fname = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        // String fname = "yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        // Initialize
        String nthName = "";
        int nthRow = 0;
        int count = 1;
        // Get number of girls' names
        int nameGirl = numGirlNames(fr);
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
        int year = 1982;
        int rank = 450;
        String gender = "M";
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




    public int yearOfHighestRank(String name, String gender) {
        // Initialize 
        int highestYear = 0;
        int highestSoFar = 0;
        // For each file
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // If this is the first file
            if (highestYear == 0) {
                FileResource fr = new FileResource(f);
                // reset highestSoFar to the number of rows
                for (CSVRecord rec : fr.getCSVParser(false)) {
                    highestSoFar ++ ;
                }
            }
            // Get year and rank of current file
            String stringYear = f.getName().substring(3, 7);
            int currentYear = Integer.parseInt(stringYear);
            int currentRank = getRank(currentYear, name, gender);
            // If current rank is higher, 
            if ((currentRank < highestSoFar) && (currentRank != -1)) {
                // Update the highestSoFar and highestYear
                highestSoFar = currentRank;
                highestYear = currentYear;
            }
        }
        // If the name and gender are not in any of the selected files, return -1
        if (highestSoFar == 0) {
            return -1;
        }
        return highestYear;
    }



    public double getAverageRank(String name, String gender) {
        // Initialize
        double avgRank = 0.0;
        double totalRank = 0.0;
        int totalCount = 0;
        // Selects a range of files to process
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // Get year and rank of current file
            String stringYear = f.getName().substring(3, 7);
            int year = Integer.parseInt(stringYear);
            totalRank += getRank(year, name, gender);
            totalCount ++;
        }
        avgRank = totalRank / totalCount;
        return avgRank;
    }




    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        //Initialize
        int totalBirths = 0;
        int myRow = getRank(year, name, gender);
        int girlBirths = 0;
        int girlRows = 0;
        int currentRow = 1;
        int currentBirth = 0;
        // Get the file
        String fname = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        // If gender is M, get number of rows with F
        if (gender == "M") {
            girlRows = numGirlNames(fr);
            myRow = myRow + girlRows; 
        }
        // Compare current row number and myRow
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (currentRow < myRow) {
                String stringBirth = rec.get(2);
                currentBirth = Integer.parseInt(stringBirth);
                // Cumulate
                totalBirths = totalBirths + currentBirth;
                currentRow ++;
                if (rec.get(1).equals("F")) {
                    girlBirths = girlBirths + currentBirth;
                }
            }
        }
        // If gender is M, deduct girlBirths
        if (gender == "M") {
            return totalBirths - girlBirths;
        }        
        return totalBirths;
    }



    public static void main (String[] args) {
        System.out.println("Start!");
        BabyNames pr = new BabyNames();
        // pr.printNames();
        // pr.testTotalBirths();
        // pr.testGetRank();
        // pr.testGetName();
        // pr.whatIsNameInYear("Owen", 1974, 2014, "M");
        // System.out.println(pr.yearOfHighestRank("Mich", "M"));
        // System.out.println(pr.getAverageRank("Robert", "M"));
        // System.out.println(pr.getTotalBirthsRankedHigher(1990, "Drew", "M"));
        // pr.testNumNames(1905, "M");
    }


}
