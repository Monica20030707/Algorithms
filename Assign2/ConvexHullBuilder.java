/***********************************************************
* Name: Monica Nguyen
* Date: 5 Jan 2023
* Course Code: CS401
* Description: This program is an algorithm to reads all 
* the points that has been created by mouse click and return
* the hull by using Graham scan algorithm.
***********************************************************/

import java.util.*;

import edu.princeton.cs.algs4.Point2D;

public class ConvexHullBuilder extends ConvexHullGUI{
    private ArrayList<Point2D> points;
    public ConvexHullBuilder(ArrayList<Point2D> points) {
        super();
        if (points == null) {
            // Handle the case when points is null
            this.points = new ArrayList<>();
        } else {
            this.points = sortByAngle(points, minY(points));
            hull();
        }
    }

    public Iterable<Point2D> hull(){
        Stack<Point2D> hull = new Stack<>();
        if (points.size() < 2) {
            // Handle the case when points ArrayList has less than 2 elements
            return hull;
        }

        hull.push(points.get(0));

        hull.push(points.get(1));

        for (int i = 2; i < points.size(); i++) {
            Point2D next= points.get(i);
            Point2D top = hull.pop();

            while (!hull.isEmpty() && Point2D.ccw(hull.peek(),top,next) <= 0) {
                top = hull.pop();
            }
            
            hull.push(top);
            hull.push(points.get(i));
        }
        
        Point2D top = hull.pop();
        if (Point2D.ccw(hull.peek(), top, minY(points)) > 0){
            hull.push(top);
        }

        return hull;
    }

    public static ArrayList<Point2D> sortByAngle(ArrayList<Point2D> pointList, Point2D firstPoint){
        Collections.sort(pointList, firstPoint.polarOrder());
        return pointList;
    }

    public static Point2D minY(ArrayList<Point2D> points) {
        // Assume the first point has the minimum y-coordinate
        Point2D minYPoint = points.get(0); 

        for (int i = 1; i < points.size(); i++) {
            Point2D currentPoint = points.get(i);
            if (currentPoint.y() < minYPoint.y()) {
                minYPoint = currentPoint;
            }
        }

        return minYPoint;
    }

    // the build-in ccw wasn't good. It takes me 1+ hours to find where am I wrong. Please 
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x());
    
        if (area2 < 0) {
            return -1;  // Clockwise orientation
        } else if (area2 > 0) {
            return 1;   // Counter-clockwise orientation
        } else {
            return 0;   // Collinear orientation
        }
    }
}