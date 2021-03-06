
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
  private Location location;
  private double distance;
  
  public DistanceFilter(Location loc,double dist){
    location = loc;
    distance = dist;
  }
  
  public boolean satisfies(QuakeEntry qe) {
     if ( location.distanceTo(qe.getLocation()) < distance){
       return true;
     }
     return false;
  }
  
  public String getName(){
       return "Distance"; 
    }
}
