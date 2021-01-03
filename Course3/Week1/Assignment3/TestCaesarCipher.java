
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Tim Wu
 * @version 0.1
 */

import edu.duke.*;

public class TestCaesarCipher {
    
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
    
    public void simpleTests() {
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        String encryptedMessage = cc.encrypt(message);
        String decryptedMessage = cc.decrypt(encryptedMessage);
        // String decryptedMessage = breakCaesarCipher(encryptedMessage);
        System.out.println("Original Message is:\n" + message + "\n");
        System.out.println("Encrypted Message is:\n" + encryptedMessage + "\n");
        System.out.println("Decrypted message is: \n" + decryptedMessage + "\n");
    }
    
    public String breakCaesarCipher(String input) {
        int [] counts = countLetters(input);
        int indexMaxCount = maxIndex(counts);
        
        int key = indexMaxCount - 4;
        if (key < 0) {
            key = 26 - key;
        }
        
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }
}
