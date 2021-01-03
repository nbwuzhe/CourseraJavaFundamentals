
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator <QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] title1 = qe1.getInfo().split("\\W+");
        String[] title2 = qe2.getInfo().split("\\W+");
        String LastWord1 = title1[title1.length - 1];
        String LastWord2 = title2[title2.length - 1];
        
        if (LastWord1.compareTo(LastWord2) == 0) {
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        else {
            return LastWord1.compareTo(LastWord2);
        }
    }
}
