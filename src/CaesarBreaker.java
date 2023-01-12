import edu.duke.*;

public class CaesarBreaker {

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


    public void testDecript(){
        String decriptMessage = decrypt("Test!");
        System.out.println(decriptMessage);
    }


    public String halfOfString(String message, int start){
        StringBuilder newString = new StringBuilder();
        for (int i=start; i<message.length(); i += 2){
            newString.append(message.charAt(i));
        }
        return newString.toString();
    }

    public int getKey(String s){
        int[] mycounts = countLetters(s);
        int maxIdx = maxIndex(mycounts);
        int dkey = maxIdx - 4;  // Assume 'e' is the most frequent letter
        if (maxIdx < 4) {
            dkey = 26 - (4-maxIdx);
        }
        return dkey;
    }


    public String decrypt(String encrypted){
        // Call CaesarCipher class
        CaesarCipher cc = new CaesarCipher();
        int dkey = getKey(encrypted);
        return cc.encrypt(encrypted, 26-dkey);
    }


    public String decryptTwoKeys(String encrypted){
        // Initialize 
        StringBuilder decrypted = new StringBuilder(encrypted);
        // Split into two strings
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        // Get the key assuming the most frequent alphabet is 'e'
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        System.out.println("key1: "+key1+", key2: "+key2);
        // Call CaesarCipher class
        CaesarCipher cc = new CaesarCipher();
        // Decrypt each string
        String firstDecrypted = cc.encrypt(firstHalf, 26-key1);
        String secondDecrypted = cc.encrypt(secondHalf, 26-key2);
        // Combine
        int counter = 0;
        for(int i=0; i<encrypted.length(); i++){
            if (i%2==0){
                decrypted.setCharAt(i, firstDecrypted.charAt(i-counter));
            }
            if (i%2==1){
                decrypted.setCharAt(i, secondDecrypted.charAt(i-counter-1));
                counter ++;
            }
        }
        return decrypted.toString();
    }


    public static void main(String[] args){
        CaesarBreaker cb = new CaesarBreaker();
        // cb.testDecript();
        // System.out.println(cb.halfOfString("Qbkm Zgis",0));

        FileResource fr = new FileResource();
        String myencrypted = fr.asString();
        System.out.println(cb.decryptTwoKeys(myencrypted));
        // System.out.println(cb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
    }

    
}
