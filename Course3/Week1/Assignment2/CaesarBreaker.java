
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */

import edu.duke.*;

public class CaesarBreaker {
    public int maxIndex(int[] values) {
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
    
    public int [] countLetters(String input) {
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
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher();
        int [] counts = countLetters(input);
        int indexMaxCount = maxIndex(counts);
        
        int keyDecrypt = indexMaxCount - 4; // 4 is the index of E in original alphabetic order
        if (keyDecrypt < 0) {
            keyDecrypt = 26 + keyDecrypt;
        }
        
        String output = cc.encrypt(input, 26 - keyDecrypt);
        
        return output;
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int m = start; m < message.length(); m = m+2) {
            sb.append(message.charAt(m));
        }
        
        return sb.toString();
    }
    
    public int getKey(String input) {
        int [] counts = countLetters(input);
        int indexOfMaxCount = maxIndex(counts);
        int keyDecrypt = indexOfMaxCount - 4; // 4 is the index of E in original alphabetic order
        if (keyDecrypt < 0) {
            keyDecrypt = 26 + keyDecrypt;
        }
        
        return keyDecrypt;
    }
    
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        
        String partEven = halfOfString(encrypted, 0);
        String partOdd = halfOfString(encrypted, 1);
        
        int keyEven = getKey(partEven);
        int keyOdd = getKey(partOdd);
        
        System.out.println("Two keys are " + keyEven + " (for even index) and " + keyOdd + " (for odd index).");
        
        String partEvenDecrypted = cc.encrypt(partEven, 26 - keyEven);
        String partOddDecrypted = cc.encrypt(partOdd, 26 - keyOdd);
        
        StringBuilder sb = new StringBuilder();
        
        int m = 0;
        while (m < partOddDecrypted.length()) { // Length of half string from odd index is either equal to or 1 less than the length of the half string from even index
            sb.append(partEvenDecrypted.charAt(m));
            sb.append(partOddDecrypted.charAt(m));
            
            m++;
        }
        
        if (partOddDecrypted.length() < partEvenDecrypted.length()) { // Fill the last character in even half string
            sb.append(partEvenDecrypted.charAt(partEvenDecrypted.length() - 1));
        }
        
        return sb.toString();
    }
    
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        // String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = cc.encrypt(message, key);
        String decrypted = decrypt(encrypted);
        System.out.println("key is " + key + "\n" + "Original Message is: \n" + message);
        System.out.println("Encrypted Message is:\n" + encrypted);
        System.out.println("Decrypted message is: \n" + decrypted);
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Encrypted Message is:\n" + message + "\n");
        System.out.println("Decrypted message is: \n" + decryptTwoKeys(message) + "\n");
    }
}
