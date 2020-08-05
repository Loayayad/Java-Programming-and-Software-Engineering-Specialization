
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){
        ThirdRatings thirdRatingObject = new ThirdRatings("data/ratings_short.csv");    
        MovieDatabase.initialize("ratedmovies_short.csv");
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+thirdRatingObject.getRaterSize());
        
        ArrayList<Rating> average = thirdRatingObject.getAverageRatings(1);
        
        System.out.println("The Number Of Movies with ratings "+average.size());
       
        Collections.sort(average,new sortByRating());
        
        for(Rating r:average){
            System.out.println(r.getValue() +" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings thirdRatingObject = new ThirdRatings("data/ratings.csv");    
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+thirdRatingObject.getRaterSize());
        
        Filter filter = new YearAfterFilter(2000);
        ArrayList<Rating> average = thirdRatingObject.getAverageRatingsByFilter(20,filter);
        
        System.out.println("The Number Of Movies with ratings "+average.size());
       
        Collections.sort(average,new sortByRating());
        
        for(Rating r:average){
            System.out.println(r.getValue() +" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings thirdRatingObject = new ThirdRatings("data/ratings.csv");    
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+thirdRatingObject.getRaterSize());
        
        Filter filter = new GenreFilter("Comedy");
        ArrayList<Rating> average = thirdRatingObject.getAverageRatingsByFilter(20,filter);
        
        System.out.println("The Number Of Movies with ratings "+average.size());
       
        Collections.sort(average,new sortByRating());
        
        for(Rating r:average){
            System.out.println(r.getValue() +" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
        
    }
    
    public void printAverageRatingsByMinutes(){

        ThirdRatings thirdRatingObject = new ThirdRatings("data/ratings.csv");    
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+thirdRatingObject.getRaterSize());
        
        Filter filter = new MinutesFilter(105,135);
        ArrayList<Rating> average = thirdRatingObject.getAverageRatingsByFilter(5,filter);
        
        System.out.println("The Number Of Movies with ratings "+average.size());
       
        Collections.sort(average,new sortByRating());
        /*
        for(Rating r:average){
            System.out.println(r.getValue() +" Time: "+MovieDatabase.getMinutes(r.getItem())
            +" "+MovieDatabase.getTitle(r.getItem()));
        }
        */
    }
    
    public void printAverageRatingsByDirectors(){

        ThirdRatings thirdRatingObject = new ThirdRatings("data/ratings.csv");    
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+thirdRatingObject.getRaterSize());
        
        Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> average = thirdRatingObject.getAverageRatingsByFilter(4,filter);
        
        System.out.println("The Number Of Movies with ratings "+average.size());
       
        Collections.sort(average,new sortByRating());
        /*
        for(Rating r:average){
            System.out.println(r.getValue() +" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
        */
   }
    
   public void printAverageRatingsByYearAfterAndGenre (){
        ThirdRatings thirdRatingObject = new ThirdRatings("data/ratings.csv");    
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+thirdRatingObject.getRaterSize());
        
        AllFilters allFilters = new AllFilters();
        Filter filter1 = new YearAfterFilter(1990);
        Filter filter2 = new GenreFilter("Drama");        
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        ArrayList<Rating> average = thirdRatingObject.getAverageRatingsByFilter(8,allFilters);
        
        System.out.println(average.size()+" movie matched");
       
        Collections.sort(average,new sortByRating());
        /*
        for(Rating r:average){
            System.out.println(r.getValue() +" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
        */
   }
   
   public void printAverageRatingsByDirectorsAndMinutes (){
        ThirdRatings thirdRatingObject = new ThirdRatings("data/ratings.csv");    
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+thirdRatingObject.getRaterSize());
        
        AllFilters allFilters = new AllFilters();
        Filter filter1 = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter filter2 = new MinutesFilter(90,180);        
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        ArrayList<Rating> average = thirdRatingObject.getAverageRatingsByFilter(3,allFilters);
        
        System.out.println(average.size()+" movie matched");
       
        Collections.sort(average,new sortByRating());
        
        for(Rating r:average){
            System.out.println(r.getValue() +" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
   }
}
