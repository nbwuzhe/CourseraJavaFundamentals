
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int order;
    private HashMap<String, ArrayList<String>> keyMap;
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        keyMap = new HashMap<>();
        order = n; // The order of this Markov model.
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);
        
        for(int k = order; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            // Delete the first char
            // and compose new key with the rest part of the old key and the new following char
            key = key.substring(1) + follows.get(index);
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return ("EfficientMarkovModel of order " + order + ".");
    }
    
    private void buildMap() {
        keyMap.clear();
        
        if (myText.length() <= order) {
            // return an empty map if order > text length
            return;
        }
        
        ArrayList<String> list;
        
        int pos = 0;
        String key;
        while (pos <= myText.length() - order) {
            key = myText.substring(pos, pos + order);
            if (!keyMap.containsKey(key)) {
                list = new ArrayList<>();
            }
            else {
                list = keyMap.get(key);
            }
            
            // Add to ArrayList except the last key in the string (no following character)
            if (pos < myText.length() - order) {
                list.add(myText.substring(pos + order, pos + order + 1));
            }
            keyMap.put(key, list);
            
            pos ++;
        }
    }
    
    public ArrayList<String> getFollows(String key) {        
        ArrayList<String> result;
        if (keyMap.containsKey(key)) {
            result = keyMap.get(key);
        }
        else {
            result = new ArrayList<>();
        }
        
        return result;
    }
    
    public void printHashMapInfo() {
        int maxListLen = 0;
        String maxListKey = "";
        
        System.out.println("Number of Keys in the HashMap: " + keyMap.size());
        
        for (String key : keyMap.keySet()) {
            if (keyMap.get(key).size() > maxListLen) {
                maxListLen = keyMap.get(key).size();
                maxListKey = key;
            }
        }
        
        System.out.println("The max list size of the key is " + maxListLen);
        System.out.println("The keys with this list size are:");
        for (String key : keyMap.keySet()) {
            if (keyMap.get(key).size() == maxListLen) {
                System.out.println(key);
            }
        }
        
    }
}
