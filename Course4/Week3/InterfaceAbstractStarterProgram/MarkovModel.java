
/**
 * Write a description of MarkovModel here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    private int order;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        order = n; // The order of this Markov model.
    }
    
    public void setTraining(String s){
        myText = s.trim();
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
        return ("MarkovModel of order " + order + ".");
    }
}
