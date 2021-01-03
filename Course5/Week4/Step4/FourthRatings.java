
/**
 * Write a description of FourthRatings here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */

import java.util.*;

public class FourthRatings {
    
    public double getAverageByID(String id, int minimalRaters) {
        double totalScore = 0.0;
        int numRaters = 0;
        for (Rater rater : RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
                totalScore += rater.getRating(id);
                numRaters ++;
            }
        }
        
        if (numRaters < minimalRaters) {
            return 0.0;
        }
        else {
            return totalScore/numRaters;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<>();
        
        for (String movieID : movies) {
            double avgScore = getAverageByID(movieID, minimalRaters);
            if (avgScore > 0.0) {
                avgRatings.add(new Rating(movieID, avgScore));
            }
        }
        
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<>();
        
        for (String movieID : movies) {
            double avgScore = getAverageByID(movieID, minimalRaters);
            if (avgScore > 0.0) {
                avgRatings.add(new Rating(movieID, avgScore));
            }
        }
        
        return avgRatings;
    }
    
    private double dotProduct(Rater me, Rater r) {
        double result = 0.0;
        for (String movieID : me.getItemsRated()) {
            if (r.hasRating(movieID)) {
                result += (me.getRating(movieID) - 5) * (r.getRating(movieID) - 5);
            }
        }
        
        return result;
    }
    
    /*
     * This method computes a similarity rating for each rater in the
     * RaterDatabase (except the rater with the ID given by the parameter)
     * Note that in each Rating object the item field is a rater¡¯s ID, and the value
     * field is the dot product comparison between that rater and the rater whose ID is
     * the parameter to getSimilarities.
     * 
     * @parameters:
     * String id: ID of a specific rater
     * 
     */
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> result = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);
        
        for (Rater rater : RaterDatabase.getRaters()) {
            String raterID = rater.getID();
            if (!raterID.equals(id)) {
                double similarity = dotProduct(me, rater);
                if (similarity > 0) {
                    result.add(new Rating(raterID, similarity));
                }
            }
        }
        Comparator ratingComp = new RatingComparator();
        Collections.sort(result, ratingComp.reversed());
        
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<>();
        
        ArrayList<Rating> allSimilarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        int numIterate = numSimilarRaters < allSimilarities.size() ? numSimilarRaters : allSimilarities.size();
        
        for (String movieID : movies) {
            double totalScore = 0.0;
            int numRaters = 0;
            for (int m = 0; m < numIterate; m ++) {
                String raterID = allSimilarities.get(m).getItem();
                double weight = allSimilarities.get(m).getValue();
                if (RaterDatabase.getRater(raterID).hasRating(movieID)) {
                    totalScore += RaterDatabase.getRater(raterID).getRating(movieID) * weight;
                    numRaters ++;
                }
            }
            
            if (numRaters >= minimalRaters){
                result.add(new Rating(movieID, totalScore/numRaters));
            }
        }
        
        Collections.sort(result, new RatingComparator());
        
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<>();
        
        ArrayList<Rating> allSimilarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        int numIterate = numSimilarRaters < allSimilarities.size() ? numSimilarRaters : allSimilarities.size();
        
        for (String movieID : movies) {
            double totalScore = 0.0;
            int numRaters = 0;
            for (int m = 0; m < numIterate; m ++) {
                String raterID = allSimilarities.get(m).getItem();
                double weight = allSimilarities.get(m).getValue();
                if (RaterDatabase.getRater(raterID).hasRating(movieID)) {
                    totalScore += RaterDatabase.getRater(raterID).getRating(movieID) * weight;
                    numRaters ++;
                }
            }
            
            if (numRaters >= minimalRaters){
                result.add(new Rating(movieID, totalScore/numRaters));
            }
        }
        
        Collections.sort(result, new RatingComparator());
        
        return result;
    }
}
