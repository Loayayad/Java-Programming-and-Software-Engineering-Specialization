
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] arrOfStr1 = qe1.getInfo().split(" ");
        String[] arrOfStr2 = qe2.getInfo().split(" ");
        if(arrOfStr1[(arrOfStr1.length)-1].compareTo(arrOfStr2[(arrOfStr2.length)-1]) == 0){
            return Double.compare(qe1.getMagnitude(),qe2.getMagnitude());
        }
        return arrOfStr1[(arrOfStr1.length)-1].compareTo(arrOfStr2[(arrOfStr2.length)-1]);
    }

}