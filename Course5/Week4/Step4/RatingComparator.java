
/**
 * Write a description of RatingComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RatingComparator implements Comparator<Rating>{
    public int compare(Rating rt1, Rating rt2) {
        return Double.compare(rt1.getValue(), rt2.getValue());
    }
}
