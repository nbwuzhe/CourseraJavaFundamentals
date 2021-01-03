import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        // return "WRITE ME!";
        StringBuffer sb = new StringBuffer();
        
        for (int m = whichSlice; m < message.length(); m += totalSlices) {
            sb.append(message.charAt(m));
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        for (int m = 0; m < klength; m++) {
            String s = sliceString(encrypted, m, klength);
            key[m] = cc.getKey(s);
        }
        
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource frMessage = new FileResource();
        DirectoryResource drDict = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        for (File f : drDict.selectedFiles()) {
            String lang = f.getName();
            FileResource frDict = new FileResource(f);
            HashSet<String> dictionary = readDictionary(frDict);
            languages.put(lang, dictionary);
        }
        
        String message = frMessage.asString();
        
        breakForAllLangs(message, languages);

        // System.out.println("Encrypted message is:\n" + message);
        // System.out.println("Decrypted message is:\n" + breakForLanguage(message, dictionary));
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        for (String line : fr.lines()) {
            line = line.toLowerCase();
            dictionary.add(line);
        }
        
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0;
        
        for (String word : words) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count ++;
            }
        }
        
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxMatchCount = 0;
        String maxMatchStr = "";
        int usedKeyLength = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        
        for (int keyLength = 1; keyLength <= 100; keyLength++) {
            int [] key = tryKeyLength(encrypted, keyLength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int matchCount = countWords(decrypted, dictionary);
            
            if (matchCount > maxMatchCount) {
                maxMatchCount = matchCount;
                maxMatchStr = decrypted;
                usedKeyLength = keyLength;
            }
            
            // Just for Question 4 in the Practice Quiz;
            /* 
            if (keyLength == 38) {
                System.out.println("Key length = 38 has valid words in decrypted message: " + matchCount);
            }
            */
        }
        
        // System.out.println("Key length used is: " + usedKeyLength);
        // System.out.println("Valid words in the decrypted message: " + maxMatchCount);
        
        return maxMatchStr;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCountMap= new HashMap<>();
        char c = 'a';
        char charMaxCount = 'a';
        int maxCount = 0;
        
        for (String word : dictionary) {
            word = word.toLowerCase();
            
            for (int m = 0; m < word.length(); m++) {
                c = word.charAt(m);
                if (!charCountMap.containsKey(c)) {
                    charCountMap.put(c, 1); 
                }
                else {
                    charCountMap.put(c, charCountMap.get(c) + 1);
                }
            }
        }
        
        for (Character cc : charCountMap.keySet()) {
            if (charCountMap.get(cc) > maxCount) {
                maxCount = charCountMap.get(cc);
                charMaxCount = cc;
            }
        }
        
        return charMaxCount;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String selectLang = "";
        String decrypted = "";
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String currentDecrypted = breakForLanguage(encrypted, dictionary);
            int currentCount = countWords(currentDecrypted, dictionary);
            if (currentCount > maxCount) {
                maxCount = currentCount;
                selectLang = language;
                decrypted = currentDecrypted;
            }
        }
        
        System.out.println("The language is: " + selectLang + "\n");
        System.out.println("The decrypted message is:\n" + decrypted);
    }
}
