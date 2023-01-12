public class WordPlay {

    public boolean isVowel(char ch){
        String vowelUpper = "AEIOU";
        String vowelLower = "aeiou";
        // Returns -1 if ch doesn't exist
        int idxUpper = vowelUpper.indexOf(ch);
        int idxLower = vowelLower.indexOf(ch);
        // If either of them 
        if ((idxUpper != -1) | (idxLower != -1)) {
            return true;
        }
        return false;
    }


    public String replaceVowels(String phrase, char ch){
        // Convert to StringBuilder
        StringBuilder encrypted = new StringBuilder(phrase);
        // Check every character
        for (int i=0; i<encrypted.length(); i++) {
            // Get current char
            char currChar = encrypted.charAt(i);
            // Check if it's a vowel
            boolean condition = isVowel(currChar);
            if (condition){
                encrypted.setCharAt(i, ch);
            }
        }
        // Return as a string
        return encrypted.toString();
    }


    public String emphasize(String phrase, char ch){
        // Unify to UpperClass
        ch = Character.toUpperCase(ch);
        // Convert to StringBuilder
        StringBuilder encrypted = new StringBuilder(phrase);
        // Check every character
        for (int i=0; i<encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            // Unify to UpperClass
            currChar = Character.toUpperCase(currChar);
            // Find ch
            if ((currChar == ch) & (i%2==0)){
                char oddChar = '*';
                encrypted.setCharAt(i, oddChar);
            }
            if ((currChar == ch) & (i%2==1)){
                char evenChar = '+';
                encrypted.setCharAt(i, evenChar);
            }
        }
        return encrypted.toString();
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        WordPlay wp = new WordPlay();
        // System.out.println(wp.isVowel('a'));
        // System.out.println(wp.replaceVowels("Hello World", '*'));
        System.out.println(wp.emphasize("Mary Bella Abracadabra", 'a'));
    }


    
}
