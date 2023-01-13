import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    // Constructor
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }


    public String encrypt(String input) {
        // Convert to StringBuilder
        StringBuilder encrypted = new StringBuilder(input);
        // Check each character
        for (int i=0; i<encrypted.length(); i++) {
            // i-th character in encrypted
            char currChar = encrypted.charAt(i);
            // its alphabetical index
            int idxUpper = alphabet.toUpperCase().indexOf(currChar);   // returns -1 if it's not in alphabet
            int idxLower = alphabet.toLowerCase().indexOf(currChar);   // returns -1 if it's not in alphabet
            // if it's an alphabet,
            if (idxUpper != -1) {
                // get the corresponding character from the shifted Alphabet reference
                char newChar = shiftedAlphabet.charAt(idxUpper);
                // Change i-th character in encrypted to the new character
                encrypted.setCharAt(i, newChar);
            }
            if (idxLower != -1) {
                // get the corresponding character from the shifted Alphabet reference
                char newChar = shiftedAlphabet.toLowerCase().charAt(idxLower);
                // Change i-th character in encrypted to the new character
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }


    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }


    // public char sensitiveConverter(char ch, int key){
    //     // String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //     // String lower = "abcdefghijklmnopqrstuvwxyz";
    //     // String shiftedUpper = upper.substring(key) + upper.substring(0, key);
    //     // String shiftedLower = lower.substring(key) + lower.substring(0, key);
    //     if (Character.isUpperCase(ch)){
    //         int idx = alphabet.indexOf(ch);
    //         ch = shiftedAlphabet.charAt(idx);
    //     }
    //     // Lower case
    //     if (Character.isLowerCase(ch)) {
    //         int idx = alphabet.toLowerCase().indexOf(ch);
    //         ch = shiftedAlphabet.toLowerCase().charAt(idx);
    //     }
    //     return ch;
    // }


    // public String encryptTwoKeys(String input, int key1, int key2){
    //     // Convert to StringBuilder
    //     StringBuilder encrypted = new StringBuilder(input);
    //     // Check every character
    //     for (int i=0; i<encrypted.length(); i++){
    //         char currChar = encrypted.charAt(i);
    //         // Use key1
    //         if (i%2==0) {
    //             char newChar = sensitiveConverter(currChar, key1);
    //             encrypted.setCharAt(i, newChar);
    //         }
    //         // Use key2
    //         if (i%2==1){
    //             char newChar = sensitiveConverter(currChar, key2);
    //             encrypted.setCharAt(i, newChar);
    //         }
    //     }
    //     return encrypted.toString();
    // }


    public void testCaesar() {
        // Select a file
        FileResource fr = new FileResource();
        // Convert to String
        String message = fr.asString();
        String encrypted = encrypt(message);
        System.out.println("<encrypted>\n"+encrypted+"\n<key> "+mainKey);
        String decrypted = decrypt(encrypted);
        System.out.println("<decrypted>\n"+decrypted);
    }


    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher(15);
        cc.testCaesar();
        // System.out.println(cc.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }


}