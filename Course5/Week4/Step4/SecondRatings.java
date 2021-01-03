
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies("data/" + moviefile);
        myRaters = fr.loadRaters("data/" + ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        ArrayList<Rating> avgRatings = new ArrayList<>();
        
        for (Movie movie : myMovies) {
            String id = movie.getID();
            double avgScore = getAverageByID(id, minimalRaters);
            if (avgScore > 0.0) {
                avgRatings.add(new Rating(id, avgScore));
            }
        }
        
        return avgRatings;
        
        // The following code uses HashMap instead of getAverageByID function, so it is much faster;
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
    
    public String getTitle(String movieID) {
        for (Movie mov : myMovies) {
            if (mov.getID().equals(movieID)) {
                return mov.getTitle();
            }
        }
        
        return "Non-existing Movie ID.";
    }
}
