
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*; 
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        buildMap();
        printHashMapInfo();
        
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with        
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");                
        for(int k=0; k < numWords-myOrder; k++){
            
            if(!myMap.containsKey(key)){
                ArrayList<String> emptyArray = new ArrayList<String>(); 
                myMap.put(key,emptyArray);
            }
            
            ArrayList<String> follows = getFollows(key);
            
            if (follows.isEmpty()) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
       return myMap.get(kGram);
    }
    
    public void buildMap(){
       
        for(int k=0; k < myText.length-(myOrder-1); k++){  
                    
            WordGram key = new WordGram(myText,k,myOrder);
	    String follow = "";
	    
            if(k+myOrder<myText.length){
                follow = myText[k+myOrder];
            }
            
            if(myMap.containsKey(key)){
                if (follow !=" "){
                 myMap.get(key).add(follow);
               }
            }
            
            else{
                ArrayList<String> list = new ArrayList<String>();
		if (follow !=" "){
                   list.add(follow);
                }
		myMap.put(key,list);
            }
        }
     }
    
    public void printHashMapInfo (){
       System.out.println("It has " +  myMap.size() + " keys in the HashMap");
       int maxSize = 0;
       for (WordGram wg : myMap.keySet()) {
           maxSize = Math.max(maxSize, myMap.get(wg).size());
           //System.out.printf("Key:\t[%s]\tvalues: ", wg);
           //System.out.println(myMap.get(wg));
		}
       System.out.println("The maximum number of values following a key is " + maxSize);
       System.out.println("Keys with the maximum size value: ");
       for (WordGram wg : myMap.keySet()) {
           if (myMap.get(wg).size()==maxSize) {
               System.out.print(wg);
               System.out.println(" (The follow words: " + myMap.get(wg) + ")");
           }
       }
    }
}
