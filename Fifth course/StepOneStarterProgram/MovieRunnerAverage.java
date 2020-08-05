
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MovieRunnerAverage {

    SecondRatings secondRatingObject = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");    
    ArrayList<Rating> average = secondRatingObject.getAverageRatings(12);
    
    public void printAverageRatings(){
        System.out.println("The Number Of Movies "+secondRatingObject.getMovieSize());
        System.out.println("The Number Of Raters "+secondRatingObject.getRaterSize());
       
        Collections.sort(average,new sortByRating());
        
        for(Rating r:average){
            System.out.println(r.getValue() +" "+secondRatingObject.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        String Id = secondRatingObject.getID("Vacation");
        
        for(Rating r:average){
            if(r.getItem().equals(Id)){
                System.out.println(r.getValue() +" "+secondRatingObject.getTitle(r.getItem()));
            }            
        }
    }
}
