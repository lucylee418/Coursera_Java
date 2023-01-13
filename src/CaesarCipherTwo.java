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
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        String decrypted = cc.encrypt(input);
        return decrypted;   
    }
    

    public static void main(String[] args) {
        CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println(cc.encrypt(message));
        // System.out.println(cc.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }

}
