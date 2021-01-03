
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    String[] myText;
    Random myRandom;
    int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
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

    public void testIndexOf() {
        String st = "this is just a test yes this is a simple test";
        String[] strArray = st.split("\\s+");
        String target = "";
        WordGram wg;
        int start = 0;
        
        // Test 1
        target = "this is";
        wg = new WordGram(target.split("\\s+"), 0, 2);
        start = 0;
        if (indexOf(strArray, wg, start) != 0) {
            System.out.println("Failed at Test 1.");
        }
        
        // Test 2
        target = "this is";
        wg = new WordGram(target.split("\\s+"), 0, 2);
        start = 3;
        if (indexOf(strArray, wg, start) != 6) {
            System.out.println("Failed at Test 2.");
        }
        
        // Test 3
        target = "this is a";
        wg = new WordGram(target.split("\\s+"), 0, 3);
        start = 0;
        if (indexOf(strArray, wg, start) != 6) {
            System.out.println("Failed at Test 3.");
        }
        
        // Test 4
        target = "a test";
        wg = new WordGram(target.split("\\s+"), 0, 2);
        start = 3;
        if (indexOf(strArray, wg, start) != 3) {
            System.out.println("Failed at Test 4.");
        }
        
        // Test 5
        target = "is a";
        wg = new WordGram(target.split("\\s+"), 0, 2);
        start = 2;
        if (indexOf(strArray, wg, start) != 7) {
            System.out.println("Failed at Test 5.");
        }
        
        // Test 6
        target = "simple test";
        wg = new WordGram(target.split("\\s+"), 0, 2);
        start = 5;
        if (indexOf(strArray, wg, start) != 9) {
            System.out.println("Failed at Test 6.");
        }
        
        System.out.println("End of the test.");
    }
}
