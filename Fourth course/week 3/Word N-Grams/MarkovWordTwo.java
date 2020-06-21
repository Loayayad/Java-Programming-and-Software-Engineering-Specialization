
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    private int indexOf(String[] words, String target, int start){
      
        return -1; 
    }
    
    public void testIndexOf(){
      String [] words = {"this" ,"is","just","a","test","yes","this","is","a","simple","test"};    
      int index = indexOf( words , "this",0);
      System.out.println(index);
      index = indexOf( words , "this",3);
      System.out.println(index);
      index = indexOf( words , "frog",0);
      System.out.println(index);
      index = indexOf( words , "frog",5);
      System.out.println(index);
      index = indexOf( words , "simple",2);
      System.out.println(index);
    }
    
    private ArrayList<String> getFollows(String key1,String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        
    for(int i=0;i<myText.length-2;i++) {
            if (key1.equals(myText[i])&&key2.equals(myText[i+1])){
            follows.add(myText[i+2]);
        }
    }
    
    /*System.out.println("key "+key1);
    System.out.println("key "+key2);
    for(String s:follows){
       System.out.println("value "+s);
    }*/
        return follows;
    }

}
