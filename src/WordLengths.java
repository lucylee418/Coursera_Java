import edu.duke.*;
import java.io.File;

public class WordLengths {

    public int[] countWordLengths(FileResource resource, int[] counts){
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
        return counts;
    }


    public int indexOfMax(int[] values){
        int maxSoFar = 0;
        int maxIdx = 0;
        for (int i=0; i<values.length; i++){
            if (values[i] > maxSoFar){
                maxSoFar = values[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        int[] mycounts = countWordLengths(fr, counts);
        int maxInd = indexOfMax(mycounts);

        System.out.println("Most frequent: "+maxInd);

        for (int i=0; i<mycounts.length; i++){
            if (mycounts[i] != 0) {
                System.out.println(mycounts[i]+" words of length "+i);
            }
        }
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
