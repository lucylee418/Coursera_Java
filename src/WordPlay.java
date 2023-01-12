import edu.duke.*;
import java.io.File;

public class WordPlay {

    public boolean isVowel(char ch){
        String vowelUpper = "AEIOU";
        String vowelLower = "aeiou";
        
        int idxUpper = vowelUpper.indexOf(ch);
        int idxLower = vowelLower.indexOf(ch);

        if ((idxUpper != -1) | (idxLower != -1)) {
            return true;
        }
        return false;
    }


    public String replaceVowels(String phrase, char ch){

        
        return "Something";
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        WordPlay wp = new WordPlay();
        System.out.println(wp.isVowel('a'));
    }


    
}
