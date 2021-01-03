import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /*
     * Location.distanceTo() returns distance in unit of meters
     * So the parameter distMax should also be in the unit of meters
     */
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (from.distanceTo(qe.getLocation()) < distMax) {
                answer.add(qe);
            }
        }
        
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            if ((title.startsWith(phrase) && where.equals("start"))
            || (title.endsWith(phrase) && where.equals("end"))
            || (title.contains(phrase) && where.equals("any"))) {
                answer.add(qe);
            }
        }
        
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> bigQuakeList = filterByMagnitude(list, 5.0);
        for (int m = 0; m < bigQuakeList.size(); m++) {
            System.out.println(bigQuakeList.get(m));
        }
        System.out.println("Found " + bigQuakeList.size() + " quakes that match that criteria.");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> closeQuakeList = filterByDistanceFrom(list, 1000000, city);
        for (int m = 0; m < closeQuakeList.size(); m++) {
            QuakeEntry qe = closeQuakeList.get(m);
            System.out.print("Distance from the city: " + qe.getLocation().distanceTo(city)/1e3 + "km. ");
            System.out.println("Earthquake location info: " + qe.getInfo());
        }
        System.out.println("Found " + closeQuakeList.size() + " quakes that match that criteria.");
    }
    
    /*
     * Parameters:
     * double minDepth and maxDepth are all in unit of meters
     */
    public void quakesOfDepth(double minDepth, double maxDepth) {
        
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> depthQuakeList = filterByDepth(list, minDepth, maxDepth);
        for (int m = 0; m < depthQuakeList.size(); m++) {
            QuakeEntry qe = depthQuakeList.get(m);
            System.out.println(qe);
        }
        System.out.println("Found " + depthQuakeList.size() + " quakes that match that criteria.");
        
    }
    
    /*
     * Parameters:
     * String where: "start", "end" or "any"
     * indicate whether the content in String phrase
     * should be found at the beginning, ending or anywhere of the "title" in each quake entry
     * 
     * String phrase: content to be searched in the "title" in each quake entry
     */
    public void quakesByPhrase(String where, String phrase) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> phraseQuakeList = filterByPhrase(list, where, phrase);
        for (int m = 0; m < phraseQuakeList.size(); m++) {
            System.out.println(phraseQuakeList.get(m));
        }
        System.out.println("Found " + phraseQuakeList.size() + " quakes that match that criteria.");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
