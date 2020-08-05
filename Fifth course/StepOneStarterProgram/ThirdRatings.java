
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings firstRatingsObject = new FirstRatings();
        myRaters = firstRatingsObject.loadRaters(ratingsfile);        
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
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movie : movieIDs){
            double average = getAverageById(movie,minimalRaters);
            if(average !=0){
                averageRatings.add(new Rating(movie,average));
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        
        for(String movie : movieIDs){
            double average = getAverageById(movie,minimalRaters);
            if(average !=0){
                averageRatings.add(new Rating(movie,average));
            }
        }
        return averageRatings;
    }
}
