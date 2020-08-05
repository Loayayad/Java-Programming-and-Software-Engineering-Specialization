
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {

    private double getAverageById (String id,int minimalRaters){
        double average = 0.0;
        int numOfRaters = 0;
        double valueOfRatings = 0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct (Rater me,Rater r){
        double dotProduct = 0;
        ArrayList<String> meRatings = me.getItemsRated();
        
        for(String movie:meRatings){
            if(r.hasRating(movie)){
                double meRating = me.getRating(movie);
                double rRating = r.getRating(movie);
                meRating -= 5;
                rRating -= 5;
                dotProduct += meRating*rRating;
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities (String id){
        ArrayList<Rating> raters = new ArrayList<>();
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r:myRaters){
            if(!r.getID().equals(id)){
                double similarity = dotProduct(me,r);
                if(similarity > 0){
                    raters.add(new Rating(r.getID(),similarity));
                }
            }
        }
        
        Collections.sort(raters, Collections.reverseOrder());
        return raters;
    }
    
    public ArrayList<Rating> getSimilarRatings (String id,int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> ratedMovies = new ArrayList<>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            double weightedAverage = 0;
            double sum = 0;
            int countRaters = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
               Rating r = list.get(i);
               double weight = r.getValue();
               String raterID = r.getItem();
               Rater myRater = RaterDatabase.getRater(raterID);
               if(myRater.hasRating(movieID)) {
                   countRaters++;
                   sum += weight * myRater.getRating(movieID);
	    	}
              }
            if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		ratedMovies.add(new Rating(movieID, weightedAverage));
	     }			
        }
        Collections.sort(ratedMovies, Collections.reverseOrder());
	return ratedMovies;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratedMovies = new ArrayList<>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            double weightedAverage = 0;
            double sum = 0;
            int countRaters = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
               Rating r = list.get(i);
               double weight = r.getValue();
               String raterID = r.getItem();
               Rater myRater = RaterDatabase.getRater(raterID);
               if(myRater.hasRating(movieID)) {
                   countRaters++;
                   sum += weight * myRater.getRating(movieID);
	    	}
              }
            if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		ratedMovies.add(new Rating(movieID, weightedAverage));
	     }			
        }
        Collections.sort(ratedMovies, Collections.reverseOrder());
	return ratedMovies;
    }
}
