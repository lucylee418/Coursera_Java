import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder myString = new StringBuilder();
        StringBuilder encrypted = new StringBuilder(message);
        for (int i=whichSlice; i<encrypted.length(); i+= totalSlices){
            myString.append(encrypted.charAt(i));
        }
        return myString.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        // System.out.println("initial key: "+key);
        CaesarCracker cc = new CaesarCracker();
        for (int i=0; i<klength; i++){
            // System.out.println("i= "+i);
            String currEncted = sliceString(encrypted, i, klength);
            // System.out.println("currEncted: "+currEncted);
            int currKey = cc.getKey(currEncted);
            key[i] = currKey;
            // System.out.println("currKey= "+currKey);
            // System.out.println("key so far: "+ key);
        }
        // System.out.println(key);
        return key;
    }


    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String myFile = fr.asString();
        System.out.println(myFile);
        int[] foundKey = tryKeyLength(myFile, 5, 'e');
        VigenereCipher vc = new VigenereCipher(foundKey);
        String decrypted = vc.decrypt(myFile);
        System.out.println(decrypted);
    }


    public static void main (String[] args){
        VigenereBreaker vb = new VigenereBreaker();

        // String myString = vb.sliceString("abcdefghijklm", 3, 4);
        // System.out.println(myString);

        // FileResource fr = new FileResource();
        // String myFile = fr.asString();
        // int[] intArray = vb.tryKeyLength(myFile, 5, 'e');
        // System.out.println(intArray);

        vb.breakVigenere();
    }
    
    
}
