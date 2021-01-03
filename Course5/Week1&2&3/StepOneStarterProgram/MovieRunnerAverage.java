
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings(int minRatingNum) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        SecondRatings sr = new SecondRatings(fnMovies, fnRaters);
        
        System.out.println("Total number of movies:" + sr.getMovieSize());
        System.out.println("Total number of raters:" + sr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minRatingNum);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t" + sr.getTitle(rating.getItem()));
        }
        
        System.out.println("Total number of such movie is " + avgRatingList.size());
    }
    
    public void getAverageRatingOneMovie(String movieTitle) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "data/ratings_short.csv";
        String fnRaters = "ratings.csv";
        SecondRatings sr = new SecondRatings(fnMovies, fnRaters);
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(0);
        
        for (Rating rating : avgRatingList) {
            if (sr.getTitle(rating.getItem()).equals(movieTitle)) {
                System.out.println("Average score of the movie \"" + movieTitle + "\" is " + rating.getValue());
                break;
            }
        }
    }
}
