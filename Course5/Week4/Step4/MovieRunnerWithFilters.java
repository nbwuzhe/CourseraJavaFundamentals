
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(int minRatingNum) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(fnRaters);
        MovieDatabase.initialize(fnMovies);
        
        System.out.println("Total number of raters:" + tr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatings(minRatingNum);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
        
        System.out.println("Total number of such movie is " + avgRatingList.size());
    }
    
    public void printAverageRatingsByYear(int minRatingNum, int year) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(fnRaters);
        Filter f = new YearAfterFilter(year);
        MovieDatabase.initialize(fnMovies);
        
        System.out.println("Total number of raters:" + tr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minRatingNum, f);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getYear(rating.getItem()) + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + avgRatingList.size());
    }
    
    public void printAverageRatingsByGenre(int minRatingNum, String genre) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(fnRaters);
        Filter f = new GenreFilter(genre);
        MovieDatabase.initialize(fnMovies);
        
        System.out.println("Total number of raters:" + tr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minRatingNum, f);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + avgRatingList.size());
    }
    
    public void printAverageRatingsByMinutes(int minRatingNum, int minMinutes, int maxMinutes) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(fnRaters);
        Filter f = new MinutesFilter(minMinutes, maxMinutes);
        MovieDatabase.initialize(fnMovies);
        
        System.out.println("Total number of raters:" + tr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minRatingNum, f);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t Time: " + MovieDatabase.getMinutes(rating.getItem()) + "min\t" + MovieDatabase.getTitle(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + avgRatingList.size());
    }
    
    public void printAverageRatingsByDirectors(int minRatingNum, String directors) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(fnRaters);
        Filter f = new DirectorsFilter(directors);
        MovieDatabase.initialize(fnMovies);
        
        System.out.println("Total number of raters:" + tr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minRatingNum, f);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t"+ MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+ MovieDatabase.getDirector(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + avgRatingList.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre(int minRatingNum, int year, String genre) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(fnRaters);
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new GenreFilter(genre));
        
        MovieDatabase.initialize(fnMovies);
        
        System.out.println("Total number of raters:" + tr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minRatingNum, f);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getYear(rating.getItem()) + "\t" + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + avgRatingList.size());
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(int minRatingNum, int minMinutes, int maxMinutes, String directors) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(fnRaters);
        
        AllFilters f = new AllFilters();
        f.addFilter(new DirectorsFilter(directors));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        
        MovieDatabase.initialize(fnMovies);
        
        System.out.println("Total number of raters:" + tr.getRaterSize());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minRatingNum, f);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t Time: " + MovieDatabase.getMinutes(rating.getItem()) + "min\t" + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+ MovieDatabase.getDirector(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + avgRatingList.size());
    }
}
