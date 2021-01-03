
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class CaesarCipherTwo {
    private String alphabet, shiftedAlphabet1, shiftedAlphabet2;
    private int mainKey1, mainKey2;

    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {       
        StringBuilder sb = new StringBuilder(input);
        for (int m = 0; m < sb.length(); m++) {
            char ch = sb.charAt(m);
            boolean flagLowCase = Character.isLowerCase(ch);
            if (flagLowCase) {
                ch = Character.toUpperCase(ch);
            }
            
            int index = alphabet.indexOf(ch);
            
            if (index != -1) {
                if (flagLowCase) {
                    if (m % 2 == 0) {
                        sb.setCharAt(m, Character.toLowerCase(shiftedAlphabet1.charAt(index)));
                    }
                    else {
                        sb.setCharAt(m, Character.toLowerCase(shiftedAlphabet2.charAt(index)));
                    }
                }
                else {
                    if (m % 2 == 0) {
                        sb.setCharAt(m, shiftedAlphabet1.charAt(index));
                    }
                    else {
                        sb.setCharAt(m, shiftedAlphabet2.charAt(index));
                    }
                }
            }
        }
        
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cct.encrypt(input);
    }
}
