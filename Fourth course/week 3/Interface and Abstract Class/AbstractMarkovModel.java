
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollow (String key){
	    ArrayList<String> follows = new ArrayList<String>();
	    int len = key.length();
	    
	    for(int i=0;i<myText.length()-len;i++) {
		if (key.equals(myText.substring(i, i+len))){
		    follows.add(myText.substring(i+len, i+len+1));
		}
	    }
	    return follows;
	}
    
}
