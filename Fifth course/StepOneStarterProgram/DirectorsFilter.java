
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorsFilter implements Filter{
    private String[] directors ;
    
    public DirectorsFilter(String dir){    
        directors = dir.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String dir:directors){
          //System.out.println(dir);
          if(MovieDatabase.getDirector(id). contains(dir)){
              return true;
          }
        }
        return false;
    }
}
