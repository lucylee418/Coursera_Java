public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    // Constructor
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input){
        // Convert to StringBuilder
        StringBuilder encrypted = new StringBuilder(input);
        // Check every character
        for (int i=0; i<encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            // Use key1
            if (i%2==0) {
                // Upperase
                if (Character.isUpperCase(currChar)){
                    int idx = alphabet.indexOf(currChar);
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                // Lowercase
                if (Character.isLowerCase(currChar)){
                    int idx = alphabet.toLowerCase().indexOf(currChar);
                    char newChar = shiftedAlphabet1.toLowerCase().charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            // Use key2
            if (i%2==1){
                 // Upperase
                 if (Character.isUpperCase(currChar)){
                    int idx = alphabet.indexOf(currChar);
                    char newChar = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                // Lowercase
                if (Character.isLowerCase(currChar)){
                    int idx = alphabet.toLowerCase().indexOf(currChar);
                    char newChar = shiftedAlphabet2.toLowerCase().charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }



    public String decrypt(String input){
        int dkey1 = mainKey1 - 4;  // Assume 'e' is the most frequent letter
        if (mainKey1 < 4) {
            dkey1 = 26 - (4-mainKey1);
        }
        int dkey2 = mainKey2 - 4;  // Assume 'e' is the most frequent letter
        if (mainKey2 < 4) {
            dkey2 = 26 - (4-mainKey2);
        }
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        String decrypted = cc.encrypt(input);
        return decrypted;   
    }
    


}
