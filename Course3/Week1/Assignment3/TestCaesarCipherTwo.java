
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Tim Wu
 * @version 0.1
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    
    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int m = start; m < message.length(); m = m+2) {
            sb.append(message.charAt(m));
        }
        
        return sb.toString();
    }
    
    private int [] countLetters(String input) {
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int [] counts = new int[26];
        
        for (int m = 0; m < input.length(); m++) {
            char ch = input.charAt(m);
            if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            
            int index = alph.indexOf(ch);
            if (index != -1) {
                counts[index] ++;
            }
        }
        
        return counts;
    }
    
    private int maxIndex(int[] values) {
        int val = values[0];
        int index = 0;
        
        for (int m = 1; m < values.length; m++) {
            if (values[m] > val) {
                val = values[m];
                index = m;
            }
        }
        
        return index;
    }
    
    private int getKey(String input) {
        int [] counts = countLetters(input);
        int indexOfMaxCount = maxIndex(counts);
        int keyDecrypt = indexOfMaxCount - 4; // 4 is the index of E in original alphabetic order
        if (keyDecrypt < 0) {
            keyDecrypt = 26 + keyDecrypt;
        }
        
        return keyDecrypt;
    }
    
    public void simpleTests() {
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        String encryptedMessage = cct.encrypt(message);
        String decryptedMessage = cct.decrypt(encryptedMessage);
        // String decryptedMessage = breakCaesarCipher(encryptedMessage);
        System.out.println("Original Message is:\n" + message + "\n");
        System.out.println("Encrypted Message is:\n" + encryptedMessage + "\n");
        System.out.println("Decrypted message is: \n" + decryptedMessage + "\n");
    }
    
    public String breakCaesarCipher(String input) {
        String partEven = halfOfString(input, 0);
        String partOdd = halfOfString(input, 1);
        
        int keyEven = getKey(partEven);
        int keyOdd = getKey(partOdd);
        
        System.out.println("Two keys are " + keyEven + " (for even index) and " + keyOdd + " (for odd index).");
        
        CaesarCipherTwo cct = new CaesarCipherTwo(keyEven, keyOdd);
        
        return cct.decrypt(input);
    }
}
