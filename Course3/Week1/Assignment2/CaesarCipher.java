
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Tim Wu
 * @version 0.1
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        String originalAlphabetic = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encryptedAlphabetic = originalAlphabetic.substring(key) + originalAlphabetic.substring(0, key);
        
        StringBuilder sb = new StringBuilder(input);
        for (int m = 0; m < sb.length(); m++) {
            char ch = sb.charAt(m);
            boolean flagLowCase = Character.isLowerCase(ch);
            if (flagLowCase) {
                ch = Character.toUpperCase(ch);
            }
            
            int index = originalAlphabetic.indexOf(ch);
            
            if (index != -1) {
                if (flagLowCase) {
                    sb.setCharAt(m, Character.toLowerCase(encryptedAlphabetic.charAt(index)));
                }
                else {
                    sb.setCharAt(m, encryptedAlphabetic.charAt(index));
                }
            }
        }
        
        return sb.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String originalAlphabetic = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encryptedAlphabetic1 = originalAlphabetic.substring(key1) + originalAlphabetic.substring(0, key1);
        String encryptedAlphabetic2 = originalAlphabetic.substring(key2) + originalAlphabetic.substring(0, key2);
        
        StringBuilder sb = new StringBuilder(input);
        for (int m = 0; m < sb.length(); m++) {
            char ch = sb.charAt(m);
            boolean flagLowCase = Character.isLowerCase(ch);
            if (flagLowCase) {
                ch = Character.toUpperCase(ch);
            }
            
            int index = originalAlphabetic.indexOf(ch);
            
            if (index != -1) {
                if (flagLowCase) {
                    if (m % 2 == 0) {
                        sb.setCharAt(m, Character.toLowerCase(encryptedAlphabetic1.charAt(index)));
                    }
                    else {
                        sb.setCharAt(m, Character.toLowerCase(encryptedAlphabetic2.charAt(index)));
                    }
                }
                else {
                    if (m % 2 == 0) {
                        sb.setCharAt(m, encryptedAlphabetic1.charAt(index));
                    }
                    else {
                        sb.setCharAt(m, encryptedAlphabetic2.charAt(index));
                    }
                }
            }
        }
        
        return sb.toString();
    }
    
    public void testEncryptTwoKeys() {
        int key1 = 8;
        int key2 = 21;
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encryptTwoKeys(message, key1, key2);
        String decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        System.out.println("Two keys are: key1 = " + key1 + " key2 = " + key2 + "\n" + "Original Message is: \n" + message);
        System.out.println("Encrypted Message is:\n" + encrypted);
        System.out.println("Decrypted message is: \n" + decrypted);
    }
    
    public void testEncrypt() {
        int key = 15;
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println("key is " + key + "\n" + "Original Message is: \n" + message);
        System.out.println("Encrypted Message is:\n" + encrypted);
        System.out.println("Decrypted message is: \n" + decrypted);
    }
}

