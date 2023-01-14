import edu.duke.*;
import java.util.*;

public class WordsWithArrays {
    StorageResouce myWords;

    public WordsWithArrays(){
        myWords = new StorageResource();
    }


    public void readWords(){
        myWords.clear();
        FileResource resource = new FileResource();
        for (Strong word : resource.words()){
            myWords.add(word.toLowerCase());
        }
    }






    
}
