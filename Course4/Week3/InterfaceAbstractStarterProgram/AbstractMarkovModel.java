
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
}
