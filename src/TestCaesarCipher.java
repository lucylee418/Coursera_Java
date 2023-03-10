import edu.duke.*;

public class TestCaesarCipher {
    
    public int[] countLetters(String word){
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

    
    public int maxIndex(int[] values){
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
        // Figure out which key was used to encrypt this message 
        int[] myCounts = countLetters(input);
        int maxIdx = maxIndex(myCounts);
        // Get the key
        int dkey = maxIdx - 4;  // Assume 'e' is the most frequent letter
        if (maxIdx < 4) {
            dkey = 26 - (4-maxIdx);
        }
        // Create a CaesarCipher object with that key and decrypt the message.
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }


    public void simpleTests(){
        FileResource fr = new FileResource();
        // Read in a file as a String
        String myString = fr.asString();
        // Create a CaesarCipher object with key 18
        CaesarCipher cc = new CaesarCipher(18);
        // Encrypt the String read in using the CaesarCipher object
        String encrypted = cc.encrypt(myString);
        // Print the encrypted String
        System.out.println("Encrypted\n"+encrypted);
        // Decrypt the String using the decrypt method.
        String decrypted = cc.decrypt(myString);
        System.out.println("Decrypted\n"+decrypted);
        // Using auto decypter
        String autoDecrypted = breakCaesarCipher(myString);
        System.out.println("Auto-decrypted\n"+autoDecrypted);
    }


    public static void main(String[] args) {
        TestCaesarCipher tcc = new TestCaesarCipher();
        tcc.simpleTests();
    }


}
