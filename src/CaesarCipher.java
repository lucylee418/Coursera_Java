import edu.duke.*;

public class CaesarCipher {

    // public String encrypt(String input, int key) {
    //     // Convert to StringBuilder
    //     StringBuilder encrypted = new StringBuilder(input);
    //     // Alphabet reference
    //     String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //     // Shifted Alphabet reference by key
    //     String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    //     // Check each character
    //     for (int i=0; i<encrypted.length(); i++) {
    //         // i-th character in encrypted
    //         char currChar = encrypted.charAt(i);
    //         currChar = Character.toUpperCase(currChar);
    //         // its alphabetical index
    //         int idx = alphabet.indexOf(currChar);   // returns -1 if it's not in alphabet
    //         // if it's an alphabet,
    //         if (idx != -1) {
    //             // get the corresponding character from the shifted Alphabet reference
    //             char newChar = shiftedAlphabet.charAt(idx);
    //             // Change i-th character in encrypted to the new character
    //             encrypted.setCharAt(i, newChar);
    //         }
    //     }
    //     return encrypted.toString();
    // }

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


    public void testCaesar() {
        int key = 17;
        // Select a file
        FileResource fr = new FileResource();
        // Convert to String
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }


    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.testCaesar();
    }


}