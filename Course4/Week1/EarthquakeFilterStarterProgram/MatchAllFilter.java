
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> allFilters;
    
    public MatchAllFilter() {
        allFilters = new ArrayList<>();
    }

    public void addFilter(Filter f) {
        allFilters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : allFilters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        
        return true;
    }
}
