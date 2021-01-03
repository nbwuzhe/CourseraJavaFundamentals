
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate () {
        int movieToSelect = 15; // The targeted number of movies to selected.
        Random random = new Random();
        
        /*
        int minMinutes = 70;
        int maxMinutes = 200;
        String directors = "J.J. Abrams,Clint Eastwood";
        
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        f.addFilter(new DirectorsFilter(directors));
        
        // ArrayList<String> movies = MovieDatabase.filterBy(f);
        */
        
        ArrayList<String> moviesAll = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> moviesSelected = new ArrayList<>();
        
        int selectedNum = 0; // How many we already selected.
        int totalMovieNum = moviesAll.size();
        while (selectedNum < movieToSelect){
            int nextMovieIndex = random.nextInt(totalMovieNum);
            if (!moviesSelected.contains(moviesAll.get(nextMovieIndex))) {
                moviesSelected.add(moviesAll.get(nextMovieIndex));
                selectedNum++;
            }
        }
        
        return moviesSelected;
    }
    
    public void printRecommendationsFor (String webRaterID){
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> recommendedMovie = fr.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        
        printHTMLStyle();
        
        if (recommendedMovie.size() == 0) {
            System.out.println("<h2>Sorry, no recommendation found.</h2>");
        }
        else {
            int dispItemNum = 20;
            if (recommendedMovie.size() < 20) {
                dispItemNum = recommendedMovie.size();
            }
            
            Comparator ratingComp = new RatingComparator();
            Collections.sort(recommendedMovie, ratingComp.reversed());

            /*
            System.out.println("Title\tTime (Minutes)\tDirectors\n");
            for (int m = 0; m < dispItemNum; m++) {
                Rating rating = recommendedMovie.get(m);
                System.out.println(MovieDatabase.getTitle(rating.getItem()) + 
                "\t" + MovieDatabase.getMinutes(rating.getItem()) + 
                "\t" + MovieDatabase.getDirector(rating.getItem()) + "\n");
            }
            */
           
            System.out.println("<h2>Movies Brought to You According To Your Ratings</h2>");
            System.out.println("<table id = \"rater\">");
            System.out.println("<tr>");
            System.out.println("<th>Rank</th>");
            System.out.println("<th>Poster</th>");
            System.out.println("<th>Title & Rating</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("<th>Country</th>");
            System.out.println("</tr>");
            
            for (int m = 0; m < dispItemNum; m++) {
                Rating rating = recommendedMovie.get(m);
                System.out.println("<tr><td>" + (m + 1) + "</td>" +
                        "<td><img src = \"" + MovieDatabase.getPoster(rating.getItem()) + "\" width=\"50\" height=\"70\"></td> " +
                        "<td>" + MovieDatabase.getYear(rating.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                        rating.getItem() + "\">" + MovieDatabase.getTitle(rating.getItem()) + "</a></td>" +
                        "<td>" + MovieDatabase.getGenres(rating.getItem()) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(rating.getItem()) + "</td>" +
                        "</tr> ");
            }
        }
    }
    
    private void printHTMLStyle() {
        System.out.println("<style>");
        System.out.println("h2,h3{");
        System.out.println("  text-align: center;");
        System.out.println("  height: 50px;");
        System.out.println("  line-height: 50px;");
        System.out.println("  font-family: Arial, Helvetica, sans- serif;");
        System.out.println("  background-color: black;");
        System.out.println("   color:  #ff6600 }");
        
        System.out.println(" table {");
        System.out.println("   border-collapse: collapse;");
        System.out.println("   margin: auto;}");
        System.out.println("table, th, td {");
        System.out.println("    border: 2px solid white;");
        System.out.println("    font-size: 15px;");
        
        System.out.println("    padding: 2px 6px 2px 6px; }");
        System.out.println(" td img{");
        System.out.println("    display: block;");
        System.out.println("    margin-left: auto;");
        System.out.println("    margin-right: auto; }");
        System.out.println("th {");
        System.out.println("    height: 40px;");
        System.out.println("    font-size: 18px;");
        
        System.out.println("  background-color: black;");
        System.out.println(" color: white;");
        System.out.println("text-align: center; }");
        
        System.out.println(" tr:nth-child(even) {");
        System.out.println("     background-color: #f2f2f2; }");
        System.out.println("  tr:nth-child(odd) {");
        System.out.println("background-color: #cccccc; }");
        System.out.println(" tr:hover {");
        System.out.println(" background-color: #666666; ");
        System.out.println("  color:white;}");
        
        System.out.println("table td:first-child {");
        System.out.println(" text-align: center; }");
        
        System.out.println(" tr {");
        System.out.println(" font-family: Arial, Helvetica, sans-serif; }");
        System.out.println(".rating{");
        System.out.println("    color:#ff6600;");
        System.out.println("    padding: 0px 10px;");
        System.out.println("   font-weight: bold; }");
        System.out.println("</style>");
    }
}
