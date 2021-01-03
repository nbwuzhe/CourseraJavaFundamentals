
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class tester {
    
    public void testTryKeyLength(int keyLength) {
        FileResource fr = new FileResource();
        String message = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        
        int [] key = vb.tryKeyLength(message, keyLength,'e');
        
        System.out.println("Keys are:");
        for (int m = 0; m < key.length; m++) {
            System.out.print(key[m] + ",");
        }
    }
}
