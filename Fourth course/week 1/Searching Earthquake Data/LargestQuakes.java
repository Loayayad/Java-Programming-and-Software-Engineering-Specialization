
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (Loay) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
  
    public void findLargestQuakes(){
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "data/nov20quakedata.atom";
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    /*
    for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
			                 
			                 }
    */			                 
    System.out.println("# quakes read: " + list.size());  
    //int indexOfTheLargestMagnitude = indexOfLargest(list);
    //System.out.println("the largest earthquake is at location "+indexOfTheLargestMagnitude+
    //" and has magnitude "+list.get(indexOfTheLargestMagnitude).getMagnitude());
    ArrayList<QuakeEntry> close = getLargest(list,50);
    for (QuakeEntry qe : close) {
         System.out.println(qe); 
    }
    System.out.println("Found "+close.size() +" quakes that matchs");
  }
  
  public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
    int indexOfTheLargestMagnitude = 0;
    double maxMagnitude = 0.0;
    for (int i = 0 ;i<quakeData.size();i++) {
       if (quakeData.get(i).getMagnitude() > maxMagnitude) {
           indexOfTheLargestMagnitude = i;
           maxMagnitude = quakeData.get(i).getMagnitude();
       }
    }
    return indexOfTheLargestMagnitude;
  }
  
  public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData,int howMany){
    ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
    ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
    if(quakeData.size()<copy.size()){
      ret = quakeData;
      return ret;
    }
    for(int j=0; j < howMany; j++) {
       int indexOfTheLargestMagnitude = indexOfLargest(copy);
       System.out.println("the largest earthquake is at location "+indexOfTheLargestMagnitude+
       " and has magnitude "+copy.get(indexOfTheLargestMagnitude).getMagnitude());
        
       ret.add(copy.get(indexOfTheLargestMagnitude));
       copy.remove(indexOfTheLargestMagnitude);
      }
    return ret;
  }
}
