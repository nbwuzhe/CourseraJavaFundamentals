import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        // Filter f = new MinMagFilter(4.0);
        // ArrayList<QuakeEntry> m7  = filter(list, f); 
        // for (QuakeEntry qe: m7) { 
            //     System.out.println(qe);
            // }
        
      /*
      Filter f1 = new MagnitudeFilter(4.0, 5.0);
      Filter f2 = new DepthFilter(-35000, -12000);
      ArrayList<QuakeEntry> f1List  = filter(list, f1);
      ArrayList<QuakeEntry> f2List  = filter(f1List, f2);
      for (QuakeEntry qe: f2List) { 
          System.out.println(qe);
      }
      */
      
      /*
      Location tokyo = new Location(35.42, 139.43);
      Filter f1 = new DistanceFilter(tokyo, 10000000);
      Filter f2 = new PhraseFilter("end", "Japan");
      ArrayList<QuakeEntry> f1List  = filter(list, f1);
      ArrayList<QuakeEntry> f2List  = filter(f1List, f2);
      for (QuakeEntry qe: f2List) { 
          System.out.println(qe);
      }
      */
      
     /*
      Location denver = new Location(39.7392, -104.9903);
      Filter f1 = new DistanceFilter(denver, 1000000);
      Filter f2 = new PhraseFilter("end", "a");
      ArrayList<QuakeEntry> f1List  = filter(list, f1);
      ArrayList<QuakeEntry> f2List  = filter(f1List, f2);
      for (QuakeEntry qe: f2List) { 
          System.out.println(qe);
      }
     */
    
      Filter f1 = new MagnitudeFilter(3.5, 4.5);
      Filter f2 = new DepthFilter(-55000, -20000);
      ArrayList<QuakeEntry> f1List  = filter(list, f1);
      ArrayList<QuakeEntry> f2List  = filter(f1List, f2);
      for (QuakeEntry qe: f2List) { 
          System.out.println(qe);
      }
    
      System.out.println(f2List.size() + " entries matches the criteria.");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter() {
       EarthQuakeParser parser = new EarthQuakeParser(); 
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       //String source = "data/nov20quakedatasmall.atom";
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+list.size()+" quakes");
       
       MatchAllFilter maf = new MatchAllFilter();
       maf.addFilter(new MagnitudeFilter(1.0, 4.0));
       maf.addFilter(new DepthFilter(-180000, -30000));
       maf.addFilter(new PhraseFilter("any", "o"));
       
       ArrayList<QuakeEntry> filteredList  = filter(list, maf);
       for (QuakeEntry qe: filteredList) { 
           System.out.println(qe);
       }
       
       System.out.println(filteredList.size() + " entries matches the criteria.");
    }
    
    public void testMatchAllFilter2() {
       EarthQuakeParser parser = new EarthQuakeParser(); 
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       //String source = "data/nov20quakedatasmall.atom";
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+list.size()+" quakes");
       
       MatchAllFilter maf = new MatchAllFilter();
       maf.addFilter(new MagnitudeFilter(0, 5));
       // Location tulsa = new Location(36.1314, -95.9372);
       // maf.addFilter(new DistanceFilter(tulsa, 10000000));
       // maf.addFilter(new PhraseFilter("any", "Ca"));
       Location billund = new Location(55.7308, 9.1153);
       maf.addFilter(new DistanceFilter(billund, 3000000));
       maf.addFilter(new PhraseFilter("any", "e"));
       
       ArrayList<QuakeEntry> filteredList  = filter(list, maf);
       for (QuakeEntry qe: filteredList) { 
           System.out.println(qe);
       }
       
       System.out.println(filteredList.size() + " entries matches the criteria.");
    }
}
