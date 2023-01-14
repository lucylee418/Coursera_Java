import edu.duke.*;
import java.util.*;
import java.io.File;

public class CharactersInPlay {
    private ArrayList<String> myChar;
    private ArrayList<Integer> myFreqs;

    public CharactersInPlay(){
        myChar = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }


    public void update(String person){
        int idx = myChar.indexOf(person);
        // Add the character’s name if it is not already there
        if (idx == -1){
            myChar.add(person);
            myFreqs.add(1);
        }
        // Count this line as one speaking part for this person. 
        else {
            int currValue = myFreqs.get(idx);
            myFreqs.set(idx, currValue+1);
        } 
    }


    public void findAllCharacters(){
        // Open a file
        FileResource fr = new FileResource();
        myChar.clear();
        myFreqs.clear();
        // Read the file line-by-line
        for (String line : fr.lines()){
        // If there is a period on the line, 
            int idx = line.indexOf(".");
            if (idx != -1){
                // Extract the possible name of the speaking part, 
                String currPerson = line.substring(0, idx).toLowerCase();
                // Call update to count it as an occurrence for this person. 
                update(currPerson);
            }
        }
    }


    public void tester(){
        // Call findAllCharacters
        findAllCharacters();
        // For each main character,
        for (int i=0; i<myChar.size(); i++){
            String currChar = myChar.get(i);
            int currFreq = myFreqs.get(i);
            // A main character is one who has more speaking parts than most people
            // You’ll have to estimate what that number should be.
            if (currFreq > 1){
                // Print out the main character, followed by the number of speaking parts
                System.out.println(currChar.toUpperCase()+": "+currFreq+" times.");
            }
        }
        charactersWithNumParts(1, 3);
    }


    public void charactersWithNumParts(int num1, int num2){ // num1 <= num2
        for (int i=0; i<myChar.size(); i++){
            String currChar = myChar.get(i);
            int currFreq = myFreqs.get(i);
            // Find chracters whose num of lines is >= num1 and <= to num2
            if (currFreq >= num1 && currFreq <= num2){
                // Print out the names of all those characters
                System.out.println(currChar.toUpperCase()+": "+currFreq+" times.");
            }
        }
    }



    public static void main (String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }


    
}
