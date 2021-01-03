
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Tim Wu
 * @version 0.1
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<> ();
        myFreqs = new ArrayList<> ();
    }
    
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            
            int index = myWords.indexOf(word);
            
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                Integer val = myFreqs.get(index);
                myFreqs.set(index,  val + 1);
            }
        }
    }
    
    private int findIndexOfMax() {
        int maxCount = 0;
        int indexMaxCount = 0;
        for (int m = 0; m < myFreqs.size(); m ++) {
            int count = myFreqs.get(m);
            if (count > maxCount) {
                maxCount = count;
                indexMaxCount = m;
            }
        }
        
        return indexMaxCount;
    }
    
    public void tester() {
        findUnique();
        
        System.out.println("Number of unique words: " + myWords.size());
        
        /*
        for(int m = 0; m < myWords.size(); m++) {
            System.out.println(myFreqs.get(m) + "\t" + myWords.get(m));
        }
        */
       
        int maxIndex = findIndexOfMax();
        
        System.out.println("The word that occurs most often and its count are: \"" + myWords.get(maxIndex) + "\"\t" + myFreqs.get(maxIndex));
    }
}
