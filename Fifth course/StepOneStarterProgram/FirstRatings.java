
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
    
    
    
    public ArrayList<Movie> loadMovies (String filename){
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            list.add(new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre")
            ,record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes"))));
        }
        return list;
    }
    
    public void testLoadMovies(){
        //ArrayList<Movie> listOfMovies = loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> listOfMovies = loadMovies("data/ratedmovies_short.csv"); 
        int countComedy = 0;
        int countMinutes = 0;
        int countDirector = 0;
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> directors = new ArrayList<>();
        
        System.out.println("There are "+listOfMovies.size()+" movies in total");
        
        for(int k=0; k < listOfMovies.size(); k++){
            //System.out.println(listOfMovies.get(k).toString());
            
            if(listOfMovies.get(k).getGenres().contains("Comedy")){
                //System.out.println(listOfMovies.get(k));
                countComedy++;
            }
                      
            if(listOfMovies.get(k).getMinutes()>150){
                //System.out.println(listOfMovies.get(k));
                countMinutes++;
            }
            
            String[] str = listOfMovies.get(k).getDirector().split(",");
            for(int j=0; j<str.length; j++){
                map.put(str[j],map.getOrDefault(str[j],0)+1);
            }    
        }
        
        int max=0;
        for(String director:map.keySet()){
            if(map.get(director)>max){
                max=map.get(director);
            }
        }

        
        System.out.println("There are "+ countComedy + " comedy movies");
        System.out.println("There are "+ countMinutes + " movies greater than 150 min");
        System.out.println("The maximum number of films directed by one director is "+max+",they are: ");
        
        for(String director:map.keySet()){
            if(map.get(director)==max){
                System.out.println(director);
                countDirector++;
            }
        }    
    }
    
    public ArrayList<Rater> loadRaters (String filename){
        ArrayList<Rater> list = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            
            boolean has = false;
            for(Rater r:list){
                if(r.getID().equals(record.get("rater_id"))){
                    r.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                    list.set(list.indexOf(r),r); 
                    has=true;
                }
            }
            if(!has){
                Rater temp = new EfficientRater(record.get("rater_id"));
                temp.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                list.add(temp); 
            }
        }
        return list;
    }
 
    public void testLoadRaters(){
        //ArrayList<Rater> listOfRatings = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> listOfRatings = loadRaters("data/ratings.csv");
        
        System.out.println("There are "+listOfRatings.size()+" Raters in total");
        /*
        for(int i=0; i<listOfRatings.size(); i++){
            
            System.out.println("Rater ID "+listOfRatings.get(i).getID()+" Number of Rated Movies by this ID "
            +listOfRatings.get(i).numRatings());
            
            for(int j=0; j<listOfRatings.get(i).numRatings(); j++){
                String str = listOfRatings.get(i).getItemsRated().get(j);
                System.out.println(str+" "+listOfRatings.get(i).getRating(str));
            }
            
        }
        */
        System.out.println("Rater ID "+listOfRatings.get(192).getID()+" Number of Rated Movies by this ID "
            +listOfRatings.get(192).numRatings());
            
        int max = 0;
        String maxid = "";
        ArrayList<String> maxRaters = new ArrayList<>();
        int numOfRates = 0;
        HashSet<String> numOfTotalMovies = new HashSet<>();
        
        for(int i=0; i<listOfRatings.size(); i++){
            if(listOfRatings.get(i).numRatings() > max){
                max = listOfRatings.get(i).numRatings();
                maxid = listOfRatings.get(i).getID();
            }
        }
        
        for(int i=0; i<listOfRatings.size(); i++){
            
            if(listOfRatings.get(i).numRatings() == max){
                maxRaters.add(listOfRatings.get(i).getID());
            }
            
            for(int j=0; j<listOfRatings.get(i).numRatings(); j++){
                String str = listOfRatings.get(i).getItemsRated().get(j);
                if(str.equals("1798709")){
                    numOfRates++;
                }
                if(!numOfTotalMovies.contains(str)){
                    numOfTotalMovies.add(str);
                }
            }
        }
        
        System.out.println("The maximum number of ratings made my a Rater is "+max+" belongs to IDs:");
        for(String s:maxRaters){
            System.out.println("ID "+s);
        }
        
        System.out.println("the number of ratings of a particular movie " +numOfRates);
        System.out.println("There were "+numOfTotalMovies.size()+" movies rated.");
    }
}
