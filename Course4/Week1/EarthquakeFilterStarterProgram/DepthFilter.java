
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{

    private double depthMax;
    private double depthMin;
    
    public DepthFilter(double dmin, double dmax) {
        depthMax = dmax;
        depthMin = dmin;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() >= depthMin && qe.getDepth() <= depthMax);
    }
}
