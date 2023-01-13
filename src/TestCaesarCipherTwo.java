import edu.duke.*;

public class TestCaesarCipherTwo {

    private String halfOfString(String message, int start){
        StringBuilder newString = new StringBuilder();
        for (int i=start; i<message.length(); i += 2){
            newString.append(message.charAt(i));
        }
        return newString.toString();
    }


    private int[] countLetters(String word){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] alphabetCounts = new int[26];
        // Check every character
        for (int i=0; i<word.length(); i++){
            char currChar = word.charAt(i);
            currChar = Character.toUpperCase(currChar);
            // Find the corresponding index in alphabet
            int alphabetIdx = alphabet.indexOf(currChar);
            // Incread the correccponding count
            if (alphabetIdx != -1) {
                alphabetCounts[alphabetIdx] += 1;
            }
        }
        return alphabetCounts;
    }


    private int maxIndex(int[] values){
        int maxSoFar = 0;
        int maxIdx = 0;
        for (int i=0; i<values.length; i++){
            if (values[i] > maxSoFar){
                maxSoFar = values[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }


    public String breakCaesarCipher(String input){
        // Split into two
        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);
        // Get frequency array 
        int[] myCounts1 = countLetters(firstHalf);
        int[] myCounts2 = countLetters(secondHalf);
        // Get the most frequent letter's index
        int maxIdx1 = maxIndex(myCounts1);
        int maxIdx2 = maxIndex(myCounts2);
        // Get the keys
        int dkey1 = maxIdx1 - 4;  // Assume 'e' is the most frequent letter
        if (maxIdx1 < 4) {
            dkey1 = 26 - (4-maxIdx1);
        }
        int dkey2 = maxIdx2 - 4;  // Assume 'e' is the most frequent letter
        if (maxIdx2 < 4) {
            dkey2 = 26 - (4-maxIdx2);
        }
        // Create a CaesarCipher object with that key and decrypt the message.
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        System.out.println("key1: "+dkey1+"  key2: "+dkey2);
        // Decrypt
        String decrypted = cc.decrypt(input);
        return decrypted;
    }


    public void simpleTests(){
        FileResource fr = new FileResource();
        // Read in a file as a String
        String myString = fr.asString();
        // Create a CaesarCipherTwo object with keys 17 and 3
        CaesarCipherTwo cct = new CaesarCipherTwo(14, 24);
        // Encrypt the String using the CaesarCipherTwo object
        String encrypted = cct.encrypt(myString);
        System.out.println("Encrypted:\n"+encrypted);
        // Decrypt the encrypted String using the decrypt method.
        String decrypted = cct.decrypt(myString);
        System.out.println("Decrypted\n"+decrypted);
        // Using auto decypter
        String autoDecrypted = breakCaesarCipher(myString);
        System.out.println("Auto-decrypted\n"+autoDecrypted);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tcc = new TestCaesarCipherTwo();
        tcc.simpleTests();
    }


    
}
