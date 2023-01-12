import edu.duke.*;

public class CaesarCipher {

    public String encrypt(String input, int key) {
        // Convert to StringBuilder
        StringBuilder encrypted = new StringBuilder(input);
        // Alphabet reference
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        // Shifted Alphabet reference by key
        String shiftedUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        // Check each character
        for (int i=0; i<encrypted.length(); i++) {
            // i-th character in encrypted
            char currChar = encrypted.charAt(i);
            // its alphabetical index
            int idxUpper = alphabetUpper.indexOf(currChar);   // returns -1 if it's not in alphabet
            int idxLower = alphabetLower.indexOf(currChar);   // returns -1 if it's not in alphabet
            // if it's an alphabet,
            if (idxUpper != -1) {
                // get the corresponding character from the shifted Alphabet reference
                char newChar = shiftedUpper.charAt(idxUpper);
                // Change i-th character in encrypted to the new character
                encrypted.setCharAt(i, newChar);
            }
            if (idxLower != -1) {
                // get the corresponding character from the shifted Alphabet reference
                char newChar = shiftedLower.charAt(idxLower);
                // Change i-th character in encrypted to the new character
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }


    public char sensitiveConverter(char ch, int key){
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedUpper = upper.substring(key) + upper.substring(0, key);
        String shiftedLower = lower.substring(key) + lower.substring(0, key);
        if (Character.isUpperCase(ch)){
            int idx = upper.indexOf(ch);
            ch = shiftedUpper.charAt(idx);
        }
        // Lower case
        if (Character.isLowerCase(ch)) {
            int idx = lower.indexOf(ch);
            ch = shiftedLower.charAt(idx);
        }
        return ch;
    }


    public String encryptTwoKeys(String input, int key1, int key2){
        // Convert to StringBuilder
        StringBuilder encrypted = new StringBuilder(input);
        // Check every character
        for (int i=0; i<encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            // Use key1
            if (i%2==0) {
                char newChar = sensitiveConverter(currChar, key1);
                encrypted.setCharAt(i, newChar);
            }
            // Use key2
            if (i%2==1){
                char newChar = sensitiveConverter(currChar, key2);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }


    public void testCaesar() {
        int key = 17;
        // Select a file
        FileResource fr = new FileResource();
        // Convert to String
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("<encrypted>\n"+encrypted+"\n<key> "+key);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println("<decrypted>\n"+decrypted);
    }


    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.testCaesar();
        // System.out.println(cc.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        // System.out.println(cc.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }


}