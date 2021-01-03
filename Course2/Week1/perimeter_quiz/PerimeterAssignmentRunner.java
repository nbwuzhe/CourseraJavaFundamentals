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
        // Put code here
        int totalNumPoints = 0;
        for (Point p : s.getPoints()) {
            totalNumPoints ++;
        }
        return totalNumPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        for (Point p : s.getPoints()) {
            if (p.getX() > largestX) {
                largestX = p.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = 0.0;
        FileResource fr;
        String fn = "";
        Shape s;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            fr = new FileResource(f);
            s = new Shape(fr);
            if (getPerimeter(s) > largestPerimeter){
                largestPerimeter = getPerimeter(s);
                fn = f.getName();
            }
        }
        
        System.out.println("File name with largest perimeter: " + fn);
        
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        // File temp = null;    // replace this code
        String fn = "";
        double largestPerimeter = 0.0;
        FileResource fr;
        Shape s;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            fr = new FileResource(f);
            s = new Shape(fr);
            if (getPerimeter(s) > largestPerimeter){
                fn = f.getName();
            }
        }
        return fn;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int totalNumPoints = getNumPoints(s);
        System.out.println("Total points = " + totalNumPoints);
        double avgLength = getAverageLength(s);
        System.out.println("Average length of sides = " + avgLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("Largest Perimeter = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("File Name with the Largest Perimeter = " + getFileWithLargestPerimeter());
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
