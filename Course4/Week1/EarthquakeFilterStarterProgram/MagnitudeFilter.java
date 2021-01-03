
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double maxMag;
    private double minMag;
    
    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag); 
    } 
}
