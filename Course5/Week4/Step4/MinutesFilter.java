
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int myMinMinutes;
    private int myMaxMinutes;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        return (MovieDatabase.getMinutes(id) >= myMinMinutes && MovieDatabase.getMinutes(id) <= myMaxMinutes);
    }

}
