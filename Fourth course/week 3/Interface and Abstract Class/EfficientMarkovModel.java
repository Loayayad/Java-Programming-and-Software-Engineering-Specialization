
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.io.*; 
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int noOfChars;
    private HashMap<String, ArrayList<String>> myMap; 

    public EfficientMarkovModel  (int numberOfChars) {
        noOfChars = numberOfChars;
        myMap = new HashMap<String, ArrayList<String>>();
    }

    public String getRandomText(int numChars){
        buildMap();
        printHashMapInfo();

        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-noOfChars);
        String key = myText.substring(index,index+noOfChars);
        sb.append(key);
        for(int k=0; k < numChars-noOfChars; k++){
            
            if(!myMap.containsKey(key)){
                ArrayList<String> emptyArray = new ArrayList<String>(); 
                myMap.put(key,emptyArray);
            }
            
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
        return "EfficientMarkovModel of order "+noOfChars;
    }

    public void buildMap(){
       
        for(int k=0; k < myText.length()-(noOfChars-1); k++){  
            
            ArrayList<String> follows = new ArrayList<String>();        
            String key = myText.substring(k,k+noOfChars);
            int len = key.length();
	    String follow = "";
	    
            if(k+noOfChars<myText.length()){
                follow = myText.substring(k+noOfChars,k+noOfChars+1);
            }
            
            if(myMap.containsKey(key)){
                myMap.get(key).add(follow);
            }
            
            else{
                ArrayList<String> list = new ArrayList<String>();
		list.add(follow);
		myMap.put(key, list);
            }
        }
    }

    public ArrayList<String> getFollow(String key){
        return myMap.get(key);
    }

    public void printHashMapInfo(){
        System.out.println("It has " +  myMap.size() + " keys in the HashMap");
        int maxSize = 0;
        for (String key : myMap.keySet()) {
            maxSize = Math.max(maxSize, myMap.get(key).size());
            //System.out.printf("Key:\t[%s]\tvalues: ", key);
            //System.out.println(myMap.get(key));
        }
        System.out.println("The maximum number of values following a key is " + maxSize);
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : myMap.keySet()) {
            if(myMap.get(key).size() == maxSize){
                keys.add(key);
            }
        }
        System.out.println("Keys that have the largest ArrayLists are: " + keys);
    }
}
