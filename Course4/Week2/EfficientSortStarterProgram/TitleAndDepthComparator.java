
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleAndDepthComparator implements Comparator <QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String title1 = qe1.getInfo();
        String title2 = qe2.getInfo();
        if (title1.compareTo(title2) == 0) {
            return Double.compare(qe1.getDepth(), qe2.getDepth());
        }
        else {
            return title1.compareTo(title2);
        }
    }
}
