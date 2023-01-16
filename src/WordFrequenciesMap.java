import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {

    public void countWords(){
        FileResource fr = new FileResource();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (String w : fr.words()){
            w = w.toLowerCase();
            // If the word w already exists,
            if (map.keySet().contains(w)){
                map.put(w, map.get(w)+1);
            }
            // Else,
            else {
                map.put(w, 1);
            }
        }
        for (String w: map.keySet()){
            int occurrences = map.get(w);
            if (occurrences > 500){
                System.out.println(occurrences+"\t"+w);
            }
        }
        
    }
 

    public static void main (String[] args) {
        WordFrequenciesMap wfm = new WordFrequenciesMap();
        wfm.countWords();
    }
    

}
