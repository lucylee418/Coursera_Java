import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }

    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        // This method should add all the words from f into the map. 
        for (String w : fr.words()){
            // If a word is already in the map, 
            if (map.keySet().contains(w)){
                // Add the current filename to its ArrayList, unless the filename is already in the ArrayList. 
                if (!map.get(w).contains(fileName)){
                    map.get(w).add(fileName);
                }
            }            
            // If a word is not in the map, 
            else{
                // Create a new ArrayList of type String with this word, 
                ArrayList<String> myArray = new ArrayList<String>();
                myArray.add(fileName);
                // and have the word map to this ArrayList. 
                map.put(w, myArray);
            }
        }

    }


    public void buildWordFileMap(){
        // Clears the map
        map.clear();
        // Uses a DirectoryResource to select a group of files
        DirectoryResource dr = new DirectoryResource();
        // For each file,
        for (File f : dr.selectedFiles()){
            // puts all of its words into the map by calling the method addWordsFromFile. 
            addWordsFromFile(f);
        }
    }


    // Returns the maximum number of files any word appears in
    public int maxNumber(){
        int maxSoFar = 0;
        for (String w : map.keySet()){
            int currSize = map.get(w).size();
            if (currSize > maxSoFar){
                maxSoFar = currSize;
            }
        }
        return maxSoFar;
    }


    // Returns an ArrayList of words that appear in exactly number files
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsArray = new ArrayList<String>();
        for (String w : map.keySet()){
            if (map.get(w).size() == number){
                wordsArray.add(w);
            }
        }
        return wordsArray;
    }


    // Prints the names of the files this word appears in, one filename per line
    public void printFilesIn(String word){
        ArrayList<String> fileArray = map.get(word);
        for (String f : fileArray){
            System.out.println(f);
        }
    }


    public void tester(){
        buildWordFileMap();
        int maxNumber = maxNumber();
        // System.out.println("Maximum number of files any word is in: "+maxNumber);
        // System.out.println("words with maximum number of files: "+wordsInNumFiles(maxNumber));
        // System.out.println("Occur 7 times: " + wordsInNumFiles(7).size()+" number of words");
        // System.out.println("Occur 4 times: "+wordsInNumFiles(4).size()+" number of words");
        // for (String w : wordsInNumFiles(maxNumber)){
        //     printFilesIn(w);
        // }
    }


    public static void main (String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
        wif.printFilesIn("tree");
    }


    
}
