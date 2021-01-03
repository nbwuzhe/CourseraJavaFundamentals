
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int m = 0; m < input.length(); m++) {
            char ch = input.charAt(m);
            boolean flagLowCase = Character.isLowerCase(ch);
            if (flagLowCase) {
                ch = Character.toUpperCase(ch);
            }
            
            int index = alphabet.indexOf(ch);
            
            if (index != -1) {
                if (flagLowCase) {
                    sb.setCharAt(m, Character.toLowerCase(shiftedAlphabet.charAt(index)));
                }
                else {
                    sb.setCharAt(m, shiftedAlphabet.charAt(index));
                }
            }
        }
        
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
