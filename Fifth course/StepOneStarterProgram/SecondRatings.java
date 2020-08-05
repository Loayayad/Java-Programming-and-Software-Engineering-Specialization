
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
        this("ratedmoviesfull.csv","ratings.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings firstRatingsObject = new FirstRatings();
        myMovies = firstRatingsObject.loadMovies(moviefile);
        myRaters = firstRatingsObject.loadRaters(ratingsfile);        
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageById (String id,int minimalRaters){
        double average = 0.0;
        int numOfRaters = 0;
        double valueOfRatings = 0.0;
        for(int i=0;i<myRaters.size();i++){
            
            for(int j=0;j<myRaters.get(i).numRatings();j++){
                String str = myRaters.get(i).getItemsRated().get(j);
                if(str.equals(id)){
                    numOfRaters++;
                    valueOfRatings+=myRaters.get(i).getRating(str);
                }
            }
        }
        
        if(numOfRaters>=minimalRaters){
            return valueOfRatings/numOfRaters;
        }
        return 0;
    }
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(Movie movie : myMovies){
            double average = getAverageById(movie.getID(),minimalRaters);
            if(average !=0){
                averageRatings.add(new Rating(movie.getID(),average));
            }
        }
        return averageRatings;
    }
    
    public String getTitle (String id){
        for(Movie movie: myMovies){
            if(movie.getID().equals(id)){
                return movie.getTitle();
            }
        }
        return "ID was not found";
    }
    
    public String getID(String title){
        for(Movie movie: myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "NO SUCh TITLE";
    }
}
