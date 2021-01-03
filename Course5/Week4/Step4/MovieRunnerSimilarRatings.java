
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(int minRatingNum) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(fnMovies);
        RaterDatabase.initialize(fnRaters);
        
        System.out.println("Total number of movies:" + MovieDatabase.size());
        System.out.println("Total number of raters:" + RaterDatabase.size());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = fr.getAverageRatings(minRatingNum);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
        
        System.out.println("Total number of such movie is " + avgRatingList.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre(int minRatingNum, int year, String genre) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        FourthRatings tr = new FourthRatings();
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new GenreFilter(genre));
        
        MovieDatabase.initialize(fnMovies);
        RaterDatabase.initialize(fnRaters);
        
        System.out.println("Total number of movies:" + MovieDatabase.size());
        System.out.println("Total number of raters:" + RaterDatabase.size());
        
        System.out.println("Average score of the movies with at least " + minRatingNum + " ratings:");
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minRatingNum, f);
        Collections.sort(avgRatingList, new RatingComparator());
        
        for (Rating rating : avgRatingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getYear(rating.getItem()) + "\t" + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + avgRatingList.size());
    }
    
    public void printSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        FourthRatings fr = new FourthRatings();

        MovieDatabase.initialize(fnMovies);
        RaterDatabase.initialize(fnRaters);
        
        System.out.println("Total number of movies:" + MovieDatabase.size());
        System.out.println("Total number of raters:" + RaterDatabase.size());
        
        ArrayList<Rating> recommendedMovie = fr.getSimilarRatings(id, numSimilarRaters, minimalRaters);

        for (Rating rating : recommendedMovie) {
            System.out.println(rating.getValue()  + "\t" +  MovieDatabase.getTitle(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + recommendedMovie.size());
    }
    
    public void printSimilarRatingsByGenre(String id, int numSimilarRaters, int minimalRaters, String genre) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        Filter f = new GenreFilter(genre);
        
        MovieDatabase.initialize(fnMovies);
        RaterDatabase.initialize(fnRaters);
        
        System.out.println("Total number of movies:" + MovieDatabase.size());
        System.out.println("Total number of raters:" + RaterDatabase.size());
        
        ArrayList<Rating> recommendedMovie = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);

        for (Rating rating : recommendedMovie) {
            System.out.println(rating.getValue()  + "\t" +  MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + recommendedMovie.size());
    }
    
    public void printSimilarRatingsByDirector(String id, int numSimilarRaters, int minimalRaters, String directors) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        Filter f = new DirectorsFilter(directors);
        
        MovieDatabase.initialize(fnMovies);
        RaterDatabase.initialize(fnRaters);
        
        System.out.println("Total number of movies:" + MovieDatabase.size());
        System.out.println("Total number of raters:" + RaterDatabase.size());
        
        ArrayList<Rating> recommendedMovie = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);

        for (Rating rating : recommendedMovie) {
            System.out.println(rating.getValue()  + "\t" +  MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + recommendedMovie.size());
    }
    
    public void printSimilarRatingsByGenreAndMinutes(String id, int numSimilarRaters, int minimalRaters, String genre, int minMinutes, int maxMinutes) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter(genre));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        
        MovieDatabase.initialize(fnMovies);
        RaterDatabase.initialize(fnRaters);
        
        System.out.println("Total number of movies:" + MovieDatabase.size());
        System.out.println("Total number of raters:" + RaterDatabase.size());
        
        ArrayList<Rating> recommendedMovie = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);

        for (Rating rating : recommendedMovie) {
            System.out.println(rating.getValue()  + "\tTime: " + MovieDatabase.getMinutes(rating.getItem()) + " min\t" + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + recommendedMovie.size());
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(String id, int numSimilarRaters, int minimalRaters, int year, int minMinutes, int maxMinutes) {
        //String fnMovies = "ratedmovies_short.csv";
        String fnMovies = "ratedmoviesfull.csv";
        //String fnRaters = "ratings_short.csv";
        String fnRaters = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        
        MovieDatabase.initialize(fnMovies);
        RaterDatabase.initialize(fnRaters);
        
        System.out.println("Total number of movies:" + MovieDatabase.size());
        System.out.println("Total number of raters:" + RaterDatabase.size());
        
        ArrayList<Rating> recommendedMovie = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);

        for (Rating rating : recommendedMovie) {
            System.out.println(rating.getValue()  + "\tTime: " + MovieDatabase.getMinutes(rating.getItem()) + " min\t"
            + " Year: " + MovieDatabase.getYear(rating.getItem()) + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
        
        System.out.println("Total number of found movie is " + recommendedMovie.size());
    }
}
