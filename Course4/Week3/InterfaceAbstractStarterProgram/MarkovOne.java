
/**
 * Write a description of MarkovOne here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    
    public MarkovOne() {
    	myRandom = new Random();
    }

    public void setTraining(String s){
    	myText = s.trim();
    }
    
    public String getRandomText(int numChars){
    	if (myText == null){
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length() - 1);
    	String key = myText.substring(index, index + 1);
    	sb.append(key);
    	
    	for(int k = 1; k < numChars; k++){
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
        return ("MarkovModel of order 1.");
    }
}
