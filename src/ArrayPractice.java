import edu.duke.*;
import java.util.Random;

public class ArrayPractice {

    public void simulate(int rolls){
        Random rand = new Random();
        int[] counts = new int[13];

        for(int i=0; i<rolls; i++){
            int d1 = rand.nextInt(6)+1;
            int d2 = rand.nextInt(6)+1;
            int mysum = d1+d2;
            counts[mysum] += 1;
        }
        for(int i=2; i<13; i++){
            System.out.println(i+": "+counts[i]);
        }
    }


    public int indexOf(String[] list, String word){
        for(int i=0; i<list.length;i++){
            if (list[i].equals(word)){
                return i;
            }
        }
        // If the word is not in the list, return -1
        return -1;
    }


    public void countWords(FileResource resource, String[] common, int[] counts){
        // Check every word in the file
        for (String word : resource.words()){
            word = word.toLowerCase();
            int index = indexOf(common, word);
            // If the word exists in comon array,
            if (index != -1){
                // increase the corresponding count
                counts[index] += 1;
            }
        }
    }


    public String[] getCommon(){
        FileResource resource = new FileResource("CommonWordsData/common.txt");
        // Will get first 20 common words
        String[] common = new String[20];
        int index = 0;
        // Check every word in the file
        for (String s:resource.words()){
            common[index] = s;
            index += 1;
        }
        return common;
    }


    public void countShakespeare(){
        // Array of files we will check
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        // Array of 20 common words
        String[] common = getCommon();
        // Initialize
        int[] counts = new int[common.length];
        // Check every file
        for(int i=0; i<plays.length;i++){
            FileResource resource = new FileResource("CommonWordsData/"+plays[i]);
            countWords(resource, common, counts);
            System.out.println("done with "+plays[i]);
        }
        for(int i=0; i<common.length; i++){
            System.out.println(common[i]+"'s count: "+counts[i]);
        }
    }



    public static void main(String[] args) {
        ArrayPractice ap = new ArrayPractice();
        // ap.simpleSimulate(10000);
        // ap.simulate(10000);
        ap.countShakespeare();
    }


    
}
