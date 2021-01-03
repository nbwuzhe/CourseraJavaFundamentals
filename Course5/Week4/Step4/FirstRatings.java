
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movList = new ArrayList<>();
        
        for (CSVRecord record : parser) {
            String anID = record.get("id");
            String aTitle = record.get("title");
            String aYear = record.get("year");
            String aCountry = record.get("country");
            String theGenres = record.get("genre");
            String aDirector = record.get("director");
            String aPoster = record.get("poster");
            int theMinutes = Integer.parseInt(record.get("minutes"));
            
            Movie mov = new Movie (anID, aTitle, aYear, theGenres, 
            aDirector, aCountry, aPoster, theMinutes);
            
            movList.add(mov);
        }
        
        return movList;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> raterList = new ArrayList<>();
        
        for (CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            String movID = record.get("movie_id");
            double score = Double.parseDouble(record.get("rating"));
            // If the current rater doesn't exist,
            // need to add a new item into the raterList
            boolean createRaterFlag = true;
            for (Rater rater : raterList) {
                if (rater.getID().equals(raterID) && !rater.hasRating(movID)) {
                    rater.addRating(movID, score);
                    createRaterFlag = false;
                    break;
                }
            }
            
            // If the current rater doesn't exist in raterListafter searching
            if (createRaterFlag) {
                // raterList.add(new PlainRater(raterID));
                raterList.add(new EfficientRater(raterID));
                // New element is always at the last of the ArrayList
                raterList.get(raterList.size()-1).addRating(movID, score);
            }
        }
        
        return raterList;
    }
    
    public void testLoadRaters() {
        //String filename = "data/ratings_short.csv";
        String filename = "data/ratings.csv";
        ArrayList<Rater> raterList = loadRaters(filename);
        String raterID = "193";
        String movieID = "1798709";
        int raterNumForMovie = 0;
        int maxRatingNum = 0;
        
        System.out.println("There are " + raterList.size() + " raters in this file.");
        
        // Print all ratings of all raters.
        /*
        for (Rater rater : raterList) {
            System.out.println("Rater with ID " + rater.getID()
            + " has " + rater.numRatings() + " ratings:");
            for (String movID : rater.getItemsRated()) {
                System.out.println("Movie ID " + movID + ": " + rater.getRating(movID));
            }
        }
        */
        
        // Find all ratings of a specific rater
        for (Rater rater : raterList) {
            if (rater.getID().equals(raterID)) {
                System.out.println("Rater with ID " + rater.getID()
                + " has " + rater.numRatings() + " ratings.");
                /*
                for (String movID : rater.getItemsRated()) {
                    System.out.println("Movie ID " + movID + ": " + rater.getRating(movID));
                }
                */
                break;
            }
        }
        
        // Find raters with max number of ratings
        for (Rater rater : raterList) {
            if (rater.numRatings() > maxRatingNum) {
                maxRatingNum = rater.numRatings();
            }
        }
        
        ArrayList<String> raterWithMaxRatingNum = new ArrayList<>();
        for (Rater rater : raterList) {
            if (rater.numRatings() == maxRatingNum) {
                raterWithMaxRatingNum.add(rater.getID());
            }
        }
        
        System.out.println("Max number of rating from a single rater is " + maxRatingNum);
        System.out.println("And there are " + raterWithMaxRatingNum.size() + " raters with such number of rating.");
        
        System.out.println("These raters are:");
        for (String id : raterWithMaxRatingNum) {
            System.out.println(id);
        }
        
        
        // Find the number of ratings a particular movie has
        for (Rater rater : raterList) {
            if (rater.hasRating(movieID)) {
                raterNumForMovie ++;
            }
        }
        System.out.println("Movie " + movieID + " was rated by " + raterNumForMovie + " raters");
        
        // Determine how many different movies have been rated by all these raters
        HashSet<String> movieNames = new HashSet<>();
        for (Rater rater : raterList) {
            for (String movieName : rater.getItemsRated()) {
                movieNames.add(movieName);
            }
        }
        System.out.println("Totally " + movieNames.size() + " movies were rated.");
    }
    
    public void testLoadMovies() {
        //String filename = "data/ratedmovies_short.csv";
        String filename = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movList = loadMovies(filename);
        String selectedGenre = "Comedy";
        int numOfSelectedGenre = 0;
        int minMinutes = 150;
        int numOfLongMovie = 0;
        int maxMovNum = 0;
        HashMap<String, Integer> directorMovNum= new HashMap<>();
        ArrayList<String> directorsWithMaxMovNum = new ArrayList<>();
        
        System.out.println("There are " + movList.size() + " movies in this file.");
        // System.out.println("\nMovies with " + selectedGenre + " genre are:");
        for (Movie mov : movList) {
            if (mov.getGenres().contains(selectedGenre)){
                numOfSelectedGenre++;
                //System.out.println(mov);
            }
        }
        System.out.println("\nThere are " + numOfSelectedGenre + " movies with " + selectedGenre + " genre.");
        
        // System.out.println("\nMovies longer than " + minMinutes + " minutes are:");
        for (Movie mov : movList) {
            if (mov.getMinutes() > minMinutes) {
                numOfLongMovie++;
                //System.out.println(mov);
            }
        }
        System.out.println("\nThere are " + numOfLongMovie + " movies longer than " + minMinutes + " minutes.");
        
        /*
        String directorList = "Simon Barrett, Jason Eisener, Gareth Evans, Gregg Hale, Eduardo S??nchez, Timo Tjahjanto, Adam ";
        String[] directors = directorList.split(", ");
        for (String s : directors) {
            System.out.println(s);
        }
        */
        
        // Calculate how many movies taken by each director
        for (Movie mov : movList) {
            String[] directors = mov.getDirector().split(", ");
            for (String director : directors) {
                if (!directorMovNum.containsKey(director)) {
                    directorMovNum.put(director, 1);
                }
                else {
                    directorMovNum.put(director, directorMovNum.get(director)+1);
                }
                if (directorMovNum.get(director) > maxMovNum) {
                    maxMovNum = directorMovNum.get(director);
                }
            }
        }
        
        System.out.println("\nThe max number of movies by any director is " + maxMovNum);
        System.out.println("And the corresponding directors are:");
        for (String director : directorMovNum.keySet()) {
            if (directorMovNum.get(director) == maxMovNum) {
                directorsWithMaxMovNum.add(director);
                System.out.println(director);
            }
        }
    }
}
