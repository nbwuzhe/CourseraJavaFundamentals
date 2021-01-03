
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters("data/" + ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double totalScore = 0.0;
        int numRaters = 0;
        for (Rater rater : myRaters) {
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
        
        
        // Below is the first version for the code in class of ThirdRating
        /*
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        ArrayList<Rating> avgRatings = new ArrayList<>();
        // key: Movie ID; value: number of ratings
        HashMap<String, Integer> movieRatingNum = new HashMap<>();
        // key: Movie ID; value: total rating score
        HashMap<String, Double> movieRatingTotal = new HashMap<>();
        
        for (String movieID : movies) {
            for (Rater rater : myRaters) {
                if (rater.hasRating(movieID)) {
                    if (!movieRatingNum.containsKey(movieID)) {
                        movieRatingNum.put(movieID, 1);
                    }
                    else {
                        movieRatingNum.put(movieID, movieRatingNum.get(movieID) + 1);
                    }
                    
                    if (!movieRatingTotal.containsKey(movieID)) {
                        movieRatingTotal.put(movieID, rater.getRating(movieID));
                    }
                    else {
                        movieRatingTotal.put(movieID, movieRatingTotal.get(movieID) + rater.getRating(movieID));
                    }
                }
            }
        }
        
        for (String movieID : movieRatingNum.keySet()) {
            if (movieRatingNum.get(movieID) >= minimalRaters) {
                double avgScore = movieRatingTotal.get(movieID) / movieRatingNum.get(movieID);
                avgRatings.add(new Rating(movieID, avgScore));
            }
        }
        
        return avgRatings;
        */
        
        // Below is the first version of getAverageRatings() function (Using HashMap) in SecondRatings
        /*
        ArrayList<Rating> avgRatings = new ArrayList<>();
        // key: Movie ID; value: number of ratings
        HashMap<String, Integer> movieRatingNum = new HashMap<>();
        // key: Movie ID; value: total rating score
        HashMap<String, Double> movieRatingTotal = new HashMap<>();
        
        for (Rater rater : myRaters) {
            
            for (String movieID : rater.getItemsRated()) {
                if (!movieRatingNum.containsKey(movieID)) {
                    movieRatingNum.put(movieID, 1);
                }
                else {
                    movieRatingNum.put(movieID, movieRatingNum.get(movieID) + 1);
                }
                
                if (!movieRatingTotal.containsKey(movieID)) {
                    movieRatingTotal.put(movieID, rater.getRating(movieID));
                }
                else {
                    movieRatingTotal.put(movieID, movieRatingTotal.get(movieID) + rater.getRating(movieID));
                }
            }
        
        }
        
        for (String movieID : movieRatingNum.keySet()) {
            if (movieRatingNum.get(movieID) >= minimalRaters) {
                double avgScore = movieRatingTotal.get(movieID) / movieRatingNum.get(movieID);
                avgRatings.add(new Rating(movieID, avgScore));
            }
        }
        
        return avgRatings;
        */

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
        
        
        // Below is the first version in the ThirdRatings class using HashMap
        /*
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        ArrayList<Rating> avgRatings = new ArrayList<>();
        // key: Movie ID; value: number of ratings
        HashMap<String, Integer> movieRatingNum = new HashMap<>();
        // key: Movie ID; value: total rating score
        HashMap<String, Double> movieRatingTotal = new HashMap<>();
        
        for (String movieID : movies) {
            for (Rater rater : myRaters) {
                if (rater.hasRating(movieID)) {
                    if (!movieRatingNum.containsKey(movieID)) {
                        movieRatingNum.put(movieID, 1);
                    }
                    else {
                        movieRatingNum.put(movieID, movieRatingNum.get(movieID) + 1);
                    }
                    
                    if (!movieRatingTotal.containsKey(movieID)) {
                        movieRatingTotal.put(movieID, rater.getRating(movieID));
                    }
                    else {
                        movieRatingTotal.put(movieID, movieRatingTotal.get(movieID) + rater.getRating(movieID));
                    }
                }
            }
        }
        
        for (String movieID : movieRatingNum.keySet()) {
            if (movieRatingNum.get(movieID) >= minimalRaters) {
                double avgScore = movieRatingTotal.get(movieID) / movieRatingNum.get(movieID);
                avgRatings.add(new Rating(movieID, avgScore));
            }
        }
        
        return avgRatings;
        */
    }
}
