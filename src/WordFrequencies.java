import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }


    public void findUnique(){
        FileResource resource = new FileResource();

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


    public void tester(){
        findUnique();
        System.out.println("# unique words: "+myWords.size());
        for(int i=0; i<myWords.size(); i++){
            // System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
        }
    }


    public static void main (String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
    

}
