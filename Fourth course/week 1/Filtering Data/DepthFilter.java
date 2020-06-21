
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
  private double minimum ;
  private double maximum ;
  
  public DepthFilter(double min,double max){
    minimum = min;
    maximum = max;
  }
  
  public boolean satisfies(QuakeEntry qe) {
     if (qe.getDepth()>=minimum && qe.getDepth() <= maximum){
       return true;   
     }
     return false;
  }
  
  public String getName(){
       return "Depth"; 
    }
}
