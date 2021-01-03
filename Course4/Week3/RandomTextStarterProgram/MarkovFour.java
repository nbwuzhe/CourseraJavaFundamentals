
/**
 * Write a description of MarkovFour here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import java.util.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> result = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length()) {
            int index = myText.indexOf(key, pos);
            if (index != -1 && index <= myText.length() - key.length() - 1) {
                result.add(myText.substring(index+key.length(), index+key.length()+1));
                pos = index + 1;
            }
            else {
                break;
            }
        }
        
        return result;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(key);
        
        for(int k = 4; k < numChars; k++){
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
}
