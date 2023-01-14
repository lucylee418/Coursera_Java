import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }


    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        // Iterate over every word in the file
        for (String s: resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                // Find the current value
                int value = myFreqs.get(index);
                // Increase by 1
                myFreqs.set(index, value+1);
            }
        }
    }

    // Returns an int that is the index location of the largest value in myFreqs. 
    public int findIndexOfMax(){
        int maxSoFar = 0;
        int maxIdx = 0;
        for (int i=0; i<myFreqs.size(); i++){
            if (myFreqs.get(i) > maxSoFar){
                maxSoFar = myFreqs.get(i);
                maxIdx = i;
            }
        }
        return maxIdx;
    }


    public void tester(){
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        for(int i=0; i<myWords.size(); i++){
            // Print the frequency of each word and the word
            System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
        }
        int idxOfMax = findIndexOfMax();
        System.out.println("The most frequent word: "+ myWords.get(idxOfMax)+", "+myFreqs.get(idxOfMax)+" times");
    }


    public static void main (String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }


}
