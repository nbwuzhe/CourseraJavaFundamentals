
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {
    public void findWebLinks() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        String lowerWord;
        int beginIndex, endIndex, pos;
        for (String word : ur.words()) {
            lowerWord = word.toLowerCase();
            pos = lowerWord.indexOf("youtube.com");
            if (pos != -1) {
                beginIndex = lowerWord.lastIndexOf("\"", pos);
                endIndex = lowerWord.indexOf("\"", pos+1);
                
                if (beginIndex != -1 && endIndex != -1) {
                    // No need to show quote marks
                    System.out.println(word.substring(beginIndex+1, endIndex));
                }
            }
        } 
    }
}
