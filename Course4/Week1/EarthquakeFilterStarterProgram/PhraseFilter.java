
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    
    public PhraseFilter(String w, String p) {
        where = w;
        phrase = p;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start") && qe.getInfo().startsWith(phrase)) {
            return true;
        }
        else if (where.equals("any") && qe.getInfo().contains(phrase)){
            return true;
        }
        else if (where.equals("end") && qe.getInfo().endsWith(phrase)) {
            return true;
        }
        
        return false;
    }

}
