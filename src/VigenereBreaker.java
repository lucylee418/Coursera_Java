import java.util.*;
import edu.duke.*;
import java.io.*;

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


    public  HashSet<String> readDictionary(FileResource fr){
        // Make a new HashSet of Strings
        HashSet<String> myHash = new  HashSet<String>();
        // Read each line in fr 
        for (String word : fr.words()){
            // convert that line to lowercase
            word = word.toLowerCase();
            // put that line into the HashSet
            myHash.add(word);
        }
        return myHash;
    }


    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        // Split the message into words
        String[] wordsArray = message.split("\\W+");
        // Iterate over those words
        for (String s : wordsArray){
            // See how many appear in the dictionary
            if (dictionary.contains(s.toLowerCase())){
                count ++;
            }
        }
        return count;
    }


    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        char common = mostCommonCharIn(dictionary);
        System.out.println("Most common character: "+common);
        int maxSoFar = 0;
        int keyLength = 0;
        // Try all key lengths from 1 to 100
        for (int i=1; i<101; i++){
            int[] foundKey = tryKeyLength(encrypted, i, common);
            // Decrypt the message
            VigenereCipher vc = new VigenereCipher(foundKey);
            String decrypted = vc.decrypt(encrypted);
            // Count how many of the “words” in it are real words, based on the dictionary passed in 
            int realWords = countWords(decrypted, dictionary);
            if (maxSoFar < realWords){
                maxSoFar = realWords;
                keyLength = i;
            }
        }
        int[] foundKey = tryKeyLength(encrypted, keyLength, 'e');
        VigenereCipher vc = new VigenereCipher(foundKey);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("key length: "+keyLength);
        System.out.println("# valid words: "+maxSoFar);
        return decrypted;
    }


    public char mostCommonCharIn(HashSet<String> dictionary){
        // Initialize frequency hashmap
        HashMap<Character, Integer> charFreq = new HashMap<Character, Integer>();
        // find out which character appears most often in the words in dictionary
        for (String w : dictionary){
            StringBuilder currWord = new StringBuilder(w);
            for (int i=0; i<w.length(); i++ ){
                char currChar = currWord.charAt(i);
                if (!charFreq.keySet().contains(currChar)){
                    charFreq.put(currChar, 1);
                }
                else{
                    charFreq.put(currChar, charFreq.get(currChar)+1);
                }
            }
        }
        char mostFreq = ' ';
        int maxSoFar = 0;
        for (char k : charFreq.keySet()){
            if (maxSoFar < charFreq.get(k)){
                maxSoFar = charFreq.get(k);
                mostFreq = k;
            }
        }
        return mostFreq;
    }



    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        String decrypted = "";
        for (String language : languages.keySet()){
            System.out.println("Try with "+language);
            HashSet<String> currDict = languages.get(language);
            decrypted = breakForLanguage(encrypted, currDict);
        }
        return decrypted;
    }



    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String myFile = fr.asString();

        // Try with many languages
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> allDicts = new HashMap<String, HashSet<String>>();
        // For each file,
        for (File f : dr.selectedFiles()){
            FileResource dfr = new FileResource(f.getName());
            String language = f.getName();
            HashSet<String> words = readDictionary(dfr);
            allDicts.put(language, words);
            System.out.println(language+" has been added.");
        }
        String decrypted = breakForAllLangs(myFile, allDicts);


        // // Try with one language
        // FileResource dfr = new FileResource("German");
        // HashSet<String> myDictionary = readDictionary(dfr);
        // String decrypted = breakForLanguage(myFile, myDictionary);
        
        System.out.println(decrypted);
    }


    public static void main (String[] args){
        VigenereBreaker vb = new VigenereBreaker();

        // String myString = vb.sliceString("abcdefghijklm", 3, 4);
        // System.out.println(myString);

        // FileResource fr = new FileResource();
        // String myFile = fr.asString();
        // int[] intArray = vb.tryKeyLength(myFile, 4, 'e');
        // System.out.println(intArray);

        vb.breakVigenere();
    }
    

}
