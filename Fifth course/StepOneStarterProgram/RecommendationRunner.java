
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender {
    private Random myRandom;
    private int toRateNum;
    private int numSimilarRaters;
    private int minimalRaters;
    private int maxRecNum;
    
    public RecommendationRunner(){
        myRandom = new Random();
        toRateNum = 15;
        numSimilarRaters = 10;
        minimalRaters = 3;
        maxRecNum = 20;
    }
    
    public ArrayList<String> getItemsToRate (){
       MovieDatabase.initialize("ratedmoviesfull.csv");
       ArrayList<String> myMovies = new ArrayList<String>();
       Filter f = new TrueFilter();
       ArrayList<String> allMovies = MovieDatabase.filterBy(f);
        
       for (int k=0; k<toRateNum; k++){
            int currIdx = myRandom.nextInt(MovieDatabase.size());;
            String currMovieID = allMovies.get(currIdx);
            // String currMovieTitle = MovieDatabase.getTitle(currMovieID);
            myMovies.add(currMovieID);
       }      
       return myMovies;
    }
    
    
    public void printRecommendationsFor (String webRaterID){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings fourth = new FourthRatings();
        ArrayList<Rating> result = fourth.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        int num = result.size();
        
        if (num == 0){
            System.out.println("Recommendation List:");
            System.out.println("Not Found");
        } else {
            if (num > maxRecNum){
                num = maxRecNum;
            }
            String header = ("<table> <tr> <th>Title</th> <th>Rating Value</th> </tr>");
            String body = "";
            
            for (int k=0; k<num; k++){
                Rating currRating = result.get(k);
                String currMovieID = currRating.getItem();
                String currMovieTitle = MovieDatabase.getTitle(currMovieID);
                double currRatingValue = currRating.getValue();
                String currGenre = MovieDatabase.getGenres(currMovieID);
                body += printOut(currMovieTitle, currRatingValue, currGenre);
            }
            
            System.out.println(header + body + "</table>");
        }          
    }
    
    private String printOut(String title, double value, String genre){
        return ("<tr> <td>" + title + "</td> <td>" + Double.toString(value) + "</td> </tr>");
    }
}
