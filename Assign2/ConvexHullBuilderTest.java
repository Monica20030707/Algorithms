/***********************************************************
* Name: Monica Nguyen
* Date: 5 Jan 2023
* Course Code: CS401
* Description: This program is an test using Junit to test
*
***********************************************************/

import java.io.*;
import java.util.*;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class ConvexHullBuilderTest {
    public static void main(String[] args) {
                // draw it you mouse please =)) I'm proud of it
                Point2D[] testCase = {
                    new Point2D(100,100),
                    new Point2D(100, 700),
                    new Point2D(700, 100),
                    new Point2D(700, 700),
                    
            };
    
            // Convert the array to an ArrayList
            ArrayList<Point2D> pointList = new ArrayList<>(Arrays.asList(testCase));
    
            // Set up the canvas and scale
            StdDraw.setCanvasSize(800, 800);
            StdDraw.setXscale(0, 1000);
            StdDraw.setYscale(0, 1000);
            StdDraw.enableDoubleBuffering();
    
            // Compute and draw the convex hull
            ConvexHullBuilder convexBuilder = new ConvexHullBuilder(pointList);
            StdDraw.clear();
            drawConvexHull(pointList, convexBuilder.hull());
    
            // Show the drawing
            StdDraw.show();
        }
    
        // The drawConvexHull, drawPoints, drawHullPoints, and drawHullLines methods remain the same.
        // Include them here for completeness.
    
        private static void drawConvexHull(ArrayList<Point2D> pointList, Iterable<Point2D> hull) {
            drawPoints(pointList);  //black points

            drawHullPoints(hull);	//red points
            
            drawHullLines(hull);	//blue lines
        }
    
        private static void drawPoints(ArrayList<Point2D> pointList) {
            int n = pointList.size(); //number of points
		
            StdDraw.setPenRadius(.01);
            StdDraw.setPenColor(StdDraw.BLACK);

            for (int i = 0; i < n; i++)
            pointList.get(i).draw();
        }
    
        private static void drawHullPoints(Iterable<Point2D> hull) {
            StdDraw.setPenColor(StdDraw.RED);
            for (Point2D p : hull)
            p.draw();
        }
    
        private static void drawHullLines(Iterable<Point2D> hull) {
            StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        Point2D prev = null;
        for (Point2D p : hull) {
            if (prev != null) 
            	prev.drawTo(p);
            prev = p;
        }
        // connect first and last points
        for (Point2D p : hull) {
            prev.drawTo(p);
            break;
        }
        }    

    }