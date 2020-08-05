
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){
        RaterDatabase.initialize("ratings_short.csv");    
        MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fourthRatingObject = new FourthRatings();    
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+RaterDatabase.size());
        
        ArrayList<Rating> average = fourthRatingObject.getAverageRatings(1);
        
        System.out.println("The Number Of Movies with ratings "+average.size());
       
        Collections.sort(average,new sortByRating());
        
        for(Rating r:average){
            System.out.println(r.getValue() +" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre (){
        RaterDatabase.initialize("ratings_short.csv");    
        MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fourthRatingObject = new FourthRatings();
        
        System.out.println("The Number Of Movies "+MovieDatabase.size());
        System.out.println("The Number Of Raters "+RaterDatabase.size());
        
        AllFilters allFilters = new AllFilters();
        Filter filter1 = new YearAfterFilter(1980);
        Filter filter2 = new GenreFilter("Romance");        
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        ArrayList<Rating> average = fourthRatingObject.getAverageRatingsByFilter(1,allFilters);
        
        System.out.println(average.size()+" movie matched");
       
        Collections.sort(average,new sortByRating());
        
        for(Rating r:average){
            System.out.println(r.getValue() +" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
        
   }
   
   public void printSimilarRatings() {
       RaterDatabase.initialize("ratings.csv ");
       System.out.println("read data for " + RaterDatabase.size() + " raters");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       FourthRatings fr = new FourthRatings();
       String raterID = "71";
       int numSimilarRaters = 20;
       int minimalRaters = 5;
       ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
       if (ratings.size() == 0 || ratings.size() == 1)
	    System.out.println(ratings.size() + " movie matched");
       else
	    System.out.println(ratings.size() + " movies matched");
       for(int i=0; i< ratings.size(); i++) {
	   if (i<15)
	    	System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
       }
   }
   
   public void printSimilarRatingsByGenre() {
        RaterDatabase.initialize("ratings.csv ");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        FourthRatings fr = new FourthRatings();
        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Mystery";
        Filter filterCriteria = new GenreFilter(genre);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        if (ratings.size() == 0 || ratings.size() == 1)
            System.out.println(ratings.size() + " movie matched");
        else
            System.out.println(ratings.size() + " movies matched");
        for(int i=0; i< ratings.size(); i++) {
            if (i<15)
                System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
        }   
   }
   
   public void printSimilarRatingsByDirector() {
	RaterDatabase.initialize("ratings.csv ");
	System.out.println("read data for " + RaterDatabase.size() + " raters");
	MovieDatabase.initialize("ratedmoviesfull.csv");
	System.out.println("read data for " + MovieDatabase.size() + " movies");
	FourthRatings fr = new FourthRatings();
	String raterID = "120";
	int numSimilarRaters = 10;
	int minimalRaters = 2;
	String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; 
	Filter filterCriteria = new DirectorsFilter(directors);
	ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	if (ratings.size() == 0 || ratings.size() == 1)
	    System.out.println(ratings.size() + " movie matched");
	else
	    System.out.println(ratings.size() + " movies matched");
	for(int i=0; i< ratings.size(); i++) {
	    if (i<15)
	    	System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
	}	    		
   }
	

   public void printSimilarRatingsByGenreAndMinutes() {
       RaterDatabase.initialize("ratings.csv ");
       System.out.println("read data for " + RaterDatabase.size() + " raters");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       FourthRatings fr = new FourthRatings();
       String raterID = "168";
       int numSimilarRaters = 10;
       int minimalRaters = 3;
       String genre = "Drama";
       int min = 80;
       int max = 160;
       AllFilters filterCriteria = new AllFilters();
       filterCriteria.addFilter(new GenreFilter(genre));
       filterCriteria.addFilter(new MinutesFilter(min, max));
       ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
       if (ratings.size() == 0 || ratings.size() == 1)
	    System.out.println(ratings.size() + " movie matched");
       else
	    System.out.println(ratings.size() + " movies matched");
       for(int i=0; i< ratings.size(); i++) {
	    if (i<15)
	    	System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
       }			
   }
   
   public void printSimilarRatingsByYearAfterAndMinutes() {
       RaterDatabase.initialize("ratings.csv ");
       System.out.println("read data for " + RaterDatabase.size() + " raters");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       FourthRatings fr = new FourthRatings();
       String raterID = "314";
       int numSimilarRaters = 10;
       int minimalRaters = 5;
       int year = 1975;
       int min = 70;
       int max = 200;
       AllFilters filterCriteria = new AllFilters();
       filterCriteria.addFilter(new YearAfterFilter(year));
       filterCriteria.addFilter(new MinutesFilter(min, max));
       ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
       if (ratings.size() == 0 || ratings.size() == 1)
	    System.out.println(ratings.size() + " movie matched");
       else
	    System.out.println(ratings.size() + " movies matched");
       for(int i=0; i< ratings.size(); i++) {
	    if (i<15)
	    	System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
	}		
   }
}
