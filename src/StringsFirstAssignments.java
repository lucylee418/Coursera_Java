import edu.duke.*;


public class StringsFirstAssignments {

    public StorageResource getAllGenes(String dna) {
        dna = dna.toUpperCase();
        StorageResource geneList = new StorageResource();
        int startidx = 0;
        while(true) {
            String currentGene = findGene(dna, startidx);
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startidx = dna.indexOf(currentGene, startidx)+currentGene.length();
        }
        return geneList;
    }

    // public void testOn(String dna) {
    //     System.out.println("Testing getAllGenes on ");
    //     StorageResource genes = getAllGenes(dna);
    //     for (String g: genes.data()) {
    //         System.out.println(g);
    //     }
    // }   

    public int findStopCodon(String dnaStr, int startidx, String stopCodon){
        int curridx = dnaStr.indexOf(stopCodon, startidx+3);
        while (curridx != -1){
            int diff = curridx - startidx;
            if (diff %3 ==0) {
                return curridx;
            }
            else {
                curridx = dnaStr.indexOf(stopCodon, curridx+1);
            }
        }
        return -1;
    }


    public String findGene(String dna, int where) {
        int startidx = dna.indexOf("ATG", where);
        if (startidx == -1) {
            return "";
        }

        int taaidx = findStopCodon(dna, startidx, "TAA");
        int tagidx = findStopCodon(dna, startidx, "TAG");
        int tgaidx = findStopCodon(dna, startidx, "TGA");
        int minidx = 0;

        if (taaidx == -1 || (tgaidx != -1 && tgaidx < taaidx)) {
            minidx = tgaidx;
        }
        else{
            minidx = taaidx;
        }

        if (minidx == -1 || (tagidx != -1 && tagidx < minidx)) {
            minidx = tagidx;
        }

        if (minidx == -1) {
            return "";
        }
        
        return dna.substring(startidx, minidx+3);
    }


    public double cgRatio(String dna){
        char[] dnaArray = dna.toCharArray();
        double numCG = 0.0;
        for (int i = 0; i < dnaArray.length; i++) {
            if (dnaArray[i] == 'C' || dnaArray[i] == 'G') {
                numCG  = numCG+1;
            }
        }
        return numCG/dna.length();
    }


    public void processGenes(StorageResource sr){
        int big9 = 0;
        int cg35 = 0;
        int longest = 0;

        for (String s: sr.data()) {
            // System.out.println("<Longer than 60 characters>");
            if (s.length()>60){
                // System.out.println(s);
                big9 = big9 + 1;
            }
            

            // System.out.println("<CG-ratio is higher than 0.35>");
            if (cgRatio(s) > 0.35){
                // System.out.println(s);
                cg35 = cg35 + 1;
            }
            

            if (s.length() > longest){
                longest = s.length();
            }
            
        }
        System.out.println(big9+" dna's are longer than 60 characters.");
        System.out.println(cg35+" dna's have CG ratio higher than 0.35.");
        System.out.println("Length of the longest dna " + longest);
    }

    public void testOn() {
        FileResource fr = new FileResource();
        String dna = fr.asString();

        System.out.println("Testing getAllGenes on ");
        StorageResource genes = getAllGenes(dna);

        int totalNum = 0;
        for (String g: genes.data()) {
            System.out.println(g);
            totalNum = totalNum +1;
        }
        
        System.out.println("Total number of DNA is "+ totalNum);
        processGenes(genes);
    }

    public static void main (String[] args) {
        System.out.println("Start!");
        StringsFirstAssignments pr = new StringsFirstAssignments();
        pr.testOn();
    }
}
