
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        
        System.out.println("running with " + markov.toString());
      //for(int k=0; k < 3; k++){
            markov.setRandom(531);
            String st= markov.getRandomText(size);
            printOut(st);
      //}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes it is a test";
        int size = 200;
        
        //MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size);
       
        //MarkovOne mOne = new MarkovOne();
        //runModel(mOne, st, size);
        
        MarkovModel mThree = new MarkovModel(7);
        runModel(mThree, st, size);
        
        //MarkovFour mFour = new MarkovFour();
        //runModel(mFour, st, size);
        
    }

    private void printOut(String s){
    String[] words = s.split("\\s+");
    int psize = 0;
    System.out.println("----------------------------------");
    for(int k=0; k < words.length; k++){
        System.out.print(words[k]+ " ");
        psize += words[k].length() + 1;
        if (psize > 60) {
            System.out.println();
            psize = 0;
        }
    }
    System.out.println("\n----------------------------------");
    }
    
    public void testHashMap (){
        FileResource fr = new FileResource("data/confucius.txt");
    String st = fr.asString();
    st = st.replace('\n', ' ');
    int size = 1000;
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        EfficientMarkovModel mThree = new EfficientMarkovModel(5);
        runModel(mThree, st, size);
    }
    
    public void compareMethods() {
    
        FileResource fr = new FileResource("data/hawthorne.txt");
    String st = fr.asString();
    st = st.replace('\n', ' ');
    int size = 1000;
        
    double startTime = System.nanoTime();
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size);
        double endTime = System.nanoTime();
        System.out.println("The running time of MarkovModel is " + (endTime-startTime)/ 1000000000.0 + " seconds");
        
        startTime = System.nanoTime();
    EfficientMarkovModel emm = new EfficientMarkovModel(2);
        runModel(emm, st, size);
        endTime = System.nanoTime();
        System.out.println("The running time of EfficientMarkovModel is " + (endTime-startTime)/ 1000000000.0 + " seconds");
    }
}
