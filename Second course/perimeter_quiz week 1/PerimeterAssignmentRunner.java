import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Start with total = 0
        int totalPoints = 0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Update totalPerim by currDist
            totalPoints = totalPoints + 1;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        double getP = getPerimeter(s);
        int getN = getNumPoints(s);
        return getP/getN;
    }

    public double getLargestSide(Shape s) {
        // Start with totalPerim = 0
        double max = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if(currDist>max){
                max=currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return max;
    }

    public double getLargestX(Shape s) {
        // Start with totalPerim = 0
        double max = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double curr = currPt.getX();
            // Update totalPerim by currDist
            if(curr>max){
                max=curr;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        double max = 0.0;
        File maxf = null;
        DirectoryResource l = new DirectoryResource();
        
        for(File f : l.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curr = getPerimeter(s);
            if(curr>max){
            max=curr;
            maxf = f;
            }
        }
        return max;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double max = 0.0;
        File maxf = null;
        DirectoryResource l = new DirectoryResource();
        
        for(File f : l.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curr = getPerimeter(s);
            if(curr>max){
            max=curr;
            maxf = f;
            }
        }
        return maxf.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int Num = getNumPoints(s);
        System.out.println("Num = " + Num);
        double Avg = getAverageLength(s);
        System.out.println("Avg = " + Avg);
        double Max = getLargestSide(s);
        System.out.println("Max = " + Max);
        double MaxX = getLargestX(s);
        System.out.println("MaxX = " + MaxX);
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
    }
    
    public void testPerimeterMultipleFiles() {
       PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
       double maxp = pr.getLargestPerimeterMultipleFiles();
       System.out.println("maxp = "+maxp);
    }

    public void testFileWithLargestPerimeter() {
       PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
       String maxf = pr.getFileWithLargestPerimeter();
       System.out.println("maxf = "+maxf);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
