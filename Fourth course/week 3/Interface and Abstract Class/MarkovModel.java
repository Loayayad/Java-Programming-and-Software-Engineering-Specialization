
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.io.*; 
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    
    private int noOfChars;
    
    public MarkovModel  (int numberOfChars) {
        noOfChars = numberOfChars;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-noOfChars);
        String key = myText.substring(index,index+noOfChars);
        sb.append(key);
        for(int k=0; k < numChars-noOfChars; k++){
            ArrayList<String> follows = getFollow(key);  
            if(follows.size() == 0){
               break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
         return "MarkovModel of order "+noOfChars;
      }
}
