
/**
 * Write a description of sortByRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class sortByRating implements Comparator<Rating> {
    public int compare(Rating qe1, Rating qe2) {
        return Double.compare(qe1.getValue(), qe2.getValue());
    }  
}
