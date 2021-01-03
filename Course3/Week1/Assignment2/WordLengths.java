
/**
 * Write a description of WordLengths here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int [] counts) {
        int len = counts.length;
        
        for (String s : resource.words()) {
            int strLen = s.length();
            if (!Character.isLetter(s.charAt(0))) {
                strLen --;
            }
            
            if (!Character.isLetter(s.charAt(s.length()-1))) {
                strLen --;
            }
            
            if (strLen <= 0) {
                continue;
            }
            else if (strLen >= counts.length - 1) { // Length of the word >= the last index 
                counts[counts.length-1] ++;
            }
            else {
                counts[strLen] ++;
            }
        }
    }
    
    public int indexOfMax (int [] values) {
        int val = values[0];
        int index = 0;
        
        for (int m = 1; m < values.length; m++) {
            if (values[m] > val) {
                val = values[m];
                index = m;
            }
        }
        
        return index;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int [] counts = new int[31];
        countWordLengths(fr, counts);
        for (int m = 1; m < counts.length; m++) {
            System.out.println("Count of word with length " + m + " is " + counts[m]);
        }
        
        System.out.println("Word length with the most count is: " + indexOfMax(counts));
    }
}
