
/**
 * Find N-largest quakes
 * 
 * @author Tim Wu
 * @version 0.1
 */

import java.util.*;

public class LargestQuakes {
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        // Tim Wu, make a copy of the input to make sure we don't modify it after execution
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        for (int m = 0; m < howMany; m++) { // How many largest earthquakes we are finding
            int index = indexOfLargest(copy);
            ret.add(copy.get(index));
            copy.remove(index);
        }

        return ret;
    }
    
    private int indexOfLargest(ArrayList<QuakeEntry> data) {
        int indexLargest = 0;
        for (int n = 1; n < data.size(); n++) {
            if (data.get(n).getMagnitude() > data.get(indexLargest).getMagnitude()) {
                indexLargest = n;
            }
        }
        
        return indexLargest;
    }

    public void findLargestQuakes(int howMany) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        ArrayList<QuakeEntry> large = getLargest(list, howMany);
        for(int k=0; k < large.size(); k++){
            System.out.println(large.get(k));
        }
        System.out.println("number found: " + large.size());
    }
    
}

