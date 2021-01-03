
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] directors;
    
    public DirectorsFilter(String directorList) {
        directors = directorList.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        for (String director : directors) {
            if (MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
        }
        return false;
    }
}
