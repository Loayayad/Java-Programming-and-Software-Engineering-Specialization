
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.io.*; 
import java.util.*;

public class MarkovModel  {
    private String myText;
    private Random myRandom;
    private int noOfChars;
    
    public MarkovModel  (int numberOfChars) {
        myRandom = new Random();
        noOfChars = numberOfChars;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollow (String key){
        ArrayList<String> follows = new ArrayList<String>();
        String text = myText;
        int index  = 0;
        
        while(true){
          index = text.indexOf(key);
          //System.out.println(index);
          if (index ==-1 || index >= text.length()-1 || index+key.length()==text.length()){
             //System.out.println("Out of boundry");
             break;
          }
          //if (text.substring(index+key.length(),index+key.length()+1).equals(" ")){
          //    follows.add("$");
          //}else{
          follows.add(text.substring(index+key.length(),index+key.length()+1));
          //}
          text = text.substring(index+1);
          //System.out.println(text);
        }
        return follows;
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
}
