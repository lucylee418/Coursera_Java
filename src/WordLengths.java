import edu.duke.*;
import java.io.File;

public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts){
        // Check every word
        for (String word: resource.words()){
            int charCount = 0;
            // Check every character
            for (int i=0; i<word.length(); i++){
                char currChar = word.charAt(i);
                currChar = Character.toTitleCase(currChar);
                // If the first and last character is non-letter, don't count
                if (((i==0||i==word.length()-1) && Character.isLetter(currChar)) || (i!=0 && i!=word.length()-1)){
                    // Increase count
                    charCount += 1;
                }
            }
            // Store in counts array
            counts[charCount] += 1;
        }
        for (int j=0; j<counts.length; j++){
            if (counts[j] != 0) {
                System.out.println(counts[j]+" words of length "+j);
            }
        }
    }

    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
    }






    public static void main (String[] args){
        WordLengths wl = new WordLengths();

        wl.testCountWordLengths();

        // FileResource myresource = new FileResource("CommonWordsData/small.txt");
        // int[] mycount = new int[20];
        // System.out.println(wl.countWordLengths(myresource, mycount));

        // String k = "Hello";
        // System.out.println(k.charAt(0));
    }




    
}
