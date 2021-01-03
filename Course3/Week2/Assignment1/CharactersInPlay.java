
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<>();
        counts = new ArrayList<>();
    }
    
    private void update (String person) {
        int index = names.indexOf(person);
        
        if (index == -1) {
            names.add(person);
            counts.add(1);
        }
        else {
            counts.set(index, counts.get(index) + 1);
        }
    }
    
    private void findAllCharacters() {
        FileResource fr = new FileResource();
        String name = "";
        
        for (String line : fr.lines()) {
            int indexOfPeriod = line.indexOf(".");
            
            if (indexOfPeriod != -1) {
                name = line.substring(0, indexOfPeriod);
                update(name);
            }
        }
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        System.out.println("\nCharacters' Counts between " + num1 + " and " + num2 + ":");
        for (int m = 0; m < names.size(); m++) {
            if (counts.get(m) >= num1 && counts.get(m) <= num2) {
                System.out.println(names.get(m) + " \t " + counts.get(m));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        
        System.out.println("Main Characters \t Counts");
        for (int m = 0; m < names.size(); m++) {
            if (counts.get(m) > 2) {
                System.out.println(names.get(m) + " \t " + counts.get(m));
            }
        }
        
        
        charactersWithNumParts(10, 15);
    }
}
