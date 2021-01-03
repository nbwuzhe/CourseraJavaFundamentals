
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    String[] myText;
    Random myRandom;
    int myOrder;
    HashMap<WordGram, ArrayList<String>> keyMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        keyMap = new HashMap<>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        // Tim Wu, just for test
        printHashMapInfo();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        String[] keyArray = new String[myOrder];
        System.arraycopy(myText, index, keyArray, 0, myOrder);
        WordGram keyWordGram = new WordGram(keyArray, 0, myOrder);
        
        // Place the first key (multiple words) into the output string
        for (int m = 0; m < myOrder; m++) {
            sb.append(keyArray[m]);
            sb.append(" ");
        }
        
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(keyWordGram);
            
            // Tim Wu, just for test
            // System.out.println("Key: \"" + keyWordGram + "\" is followed by: " + follows);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keyWordGram = keyWordGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start) { 
        for (int m = start; m <= words.length - target.length(); m++) {
            String[] currentWords = new String[target.length()];
            System.arraycopy(words, m, currentWords, 0, target.length());
            WordGram currentWordGram = new WordGram(currentWords, 0, target.length());
            if (target.equals(currentWordGram)) {
                return m;
            }
        }
        
        return -1;
    }
    
    /*
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length ) {
            int index = indexOf(myText, kGram, pos);
            if (index != -1 && index + 1 < myText.length) {
                follows.add(myText[index + kGram.length()]);
            }
            else {    
                break;
            }
            
            pos = index + 1;
        }
        return follows;
    }
    */
    
   private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows;
        if (keyMap.containsKey(kGram)) {
            follows = keyMap.get(kGram);
        }
        else {
            follows = new ArrayList<>();
        }
        
        return follows;
    }
   
    private void buildMap() {
        keyMap.clear();
        
        if (myText.length <= myOrder) {
            // return an empty map if order > text length
            return;
        }
        
        ArrayList<String> list;
        
        int pos = 0;
        // String key;
        
        WordGram keyWG;
        while (pos <= myText.length - myOrder) {
            String[] keyArray = new String[myOrder];
            System.arraycopy(myText, pos, keyArray, 0, myOrder);
            keyWG = new WordGram(keyArray, 0, myOrder);
            // key = myText.substring(pos, pos + myOrder);
            if (!keyMap.containsKey(keyWG)) {
                list = new ArrayList<>();
            }
            else {
                list = keyMap.get(keyWG);
            }
            
            // Add to ArrayList except the last key in the string (no following character)
            if (pos < myText.length - myOrder) {
                // list.add(myText.substring(pos + order, pos + order + 1));
                list.add(myText[pos + myOrder]);
            }
            
            keyMap.put(keyWG, list);
            
            pos ++;
        }
    }
    
    public void printHashMapInfo() {
        int maxListLen = 0;
        WordGram maxListKey;
        
        /*
        System.out.println("The full keyMap is shown below:");
        for (WordGram key : keyMap.keySet()) {
            System.out.println("\"" + key + "\": " + keyMap.get(key));
        }
        */
        
        System.out.println("\nNumber of Keys in the HashMap: " + keyMap.size());
        
        for (WordGram key : keyMap.keySet()) {
            if (keyMap.get(key).size() > maxListLen) {
                maxListLen = keyMap.get(key).size();
                maxListKey = key;
            }
        }
        
        System.out.println("\nThe max list size of the key is " + maxListLen);
        System.out.println("The keys with this list size are:");
        for (WordGram key : keyMap.keySet()) {
            if (keyMap.get(key).size() == maxListLen) {
                System.out.println("\"" + key + "\": " + keyMap.get(key));
            }
        }
        
    }
}
