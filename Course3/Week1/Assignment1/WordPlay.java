
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordPlay {
    public boolean isVowel (char ch) {
        ch = Character.toLowerCase(ch);
        
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        
        return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        
        for (int m = 0; m < sb.length(); m++) {
            if (isVowel(sb.charAt(m))) {
                sb.setCharAt(m, ch);
            }
        }
        
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        
        for (int m = 0; m < sb.length(); m++) {
            if (sb.charAt(m) == Character.toUpperCase(ch) || sb.charAt(m) == Character.toLowerCase(ch)) {
                if (m % 2 == 0) { // Odd number location / Even index
                    sb.setCharAt(m, '*');
                }
                else { // Even number location / Odd index
                    sb.setCharAt(m, '+');
                }
            }
        }
        
        return sb.toString();
    }
    
    public void testIsVowel() {
        char chTest;
        String result;
        
        chTest = 'F';
        result = isVowel(chTest) ? "" : " not";
        System.out.println("Character " + chTest + " is" + result + " a vowel.");
        
        chTest = 'E';
        result = isVowel(chTest) ? "" : " not";
        System.out.println("Character " + chTest + " is" + result + " a vowel.");
        
        chTest = 'z';
        result = isVowel(chTest) ? "" : " not";
        System.out.println("Character " + chTest + " is"  + result + " a vowel.");
        
        chTest = 'U';
        result = isVowel(chTest) ? "" : " not";
        System.out.println("Character " + chTest + " is" + result + " a vowel.");
        
        chTest = 'I';
        result = isVowel(chTest) ? "" : " not";
        System.out.println("Character " + chTest + " is" + result + " a vowel.");
        
        chTest = 'a';
        result = isVowel(chTest) ? "" : " not";
        System.out.println("Character " + chTest + " is" + result + " a vowel.");
    }
    
}
