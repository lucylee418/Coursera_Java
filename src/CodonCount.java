import edu.duke.*;
import java.util.*;

public class CodonCount {

    private HashMap<String, Integer> map;

    public CodonCount(){
        map = new HashMap<String, Integer>();
    }


    public void buildCodonMap(int start, String dna){
        map.clear();
        // Reading frame
        for(int i=0; start+i+2<dna.length(); i+=3){
            String currCodon = dna.substring(start+i, start+i+3);
            if (map.keySet().contains(currCodon)){
                map.put(currCodon, map.get(currCodon)+1);
            }
            else {
                map.put(currCodon, 1);
            }
        }
    }


    public String getMostCommonCodon(){
        int maxSoFar = 0;
        String maxKey = "";
        for (String c : map.keySet()){
            if (map.get(c) > maxSoFar){
                maxKey = c;
                maxSoFar = map.get(c);
            }
        }
        return maxKey;
    }


    public void printCodonCounts(int start, int end){
        for (String c: map.keySet()){
            int occurrences = map.get(c);
            if (occurrences >= start && occurrences <= end){
                System.out.println(c + "'s occurrences: "+occurrences);
            }
        }
    }


    public void tester(){
        FileResource fr = new FileResource();
        for (String codons : fr.words()){
            String codon = codons.toUpperCase();
            for (int i=0; i<3; i++){
                buildCodonMap(i, codon);
                System.out.println("<Reading "+i+">");
                System.out.println("Number of unique codons: "+map.size());
                String myCommon = getMostCommonCodon();
                System.out.println("Most common: "+myCommon+", "+map.get(myCommon)+" times.");
                System.out.println("Between 1 and 6:");
                printCodonCounts(1, 6);
            }
            
        }        
    }



    public static void main (String[] args) {
        CodonCount cc = new CodonCount();
        // cc.buildCodonMap(2, "CGTTCAAGTTCAA");
        cc.tester();
    }



}
