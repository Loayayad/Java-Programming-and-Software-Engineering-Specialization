
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*; 
import java.util.*;
import edu.duke.*;

public class Tester {
    
    public void testGetFollows (){
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes a test.";
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollow("o");

        
        for (String s : follows){
            System.out.println(s);
        }
        System.out.println(follows.size());
    }
    
    public void testGetFollowsWithFile (){
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollow("he");
        markov.setRandom(42);
        for (String s : follows){
            System.out.println(s);
        }
        System.out.println(follows.size());
    }
}
