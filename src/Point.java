/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dragsa
 */
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new PointSlopeComparator();       // YOUR DEFINITION HERE
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

//The compareTo() method should compare points by their y-coordinates, breaking ties by their x-coordinates.
//Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) if and only if
//either y0 < y1 or if y0 = y1 and x0 < x1.
//
//The slopeTo() method should return the slope between the invoking point (x0, y0) and the
//argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0).
//Treat the slope of a horizontal line segment as positive zero;
//treat the slope of a vertical line segment as positive infinity;
//treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
//
    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }
        if (this.compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y) {
            return 0.0;
        }
        return ((that.y - this.y) /(that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }
        if (this.y > that.y) {
            return 1;
        }
        if (this.y < that.y) {
            return -1;
        }
        if (this.x > that.x) {
            return 1;
        }
        if (this.x < that.x) {
            return -1;
        }
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

//The SLOPE_ORDER comparator should compare points by the slopes they make with the invoking point (x0, y0).
//Formally, the point (x1, y1) is less than the point (x2, y2) if and only if
//the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
//Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
    private class PointSlopeComparator implements Comparator<Point> {

        public int compare(Point pointA, Point pointB) {
            if (pointA == null || pointB == null) {
                throw new NullPointerException();
            }
            if (slopeTo(pointA) > slopeTo(pointB)) {
                return 1;
            }
            if (slopeTo(pointA) < slopeTo(pointB)) {
                return -1;
            }
            return 0;
        }
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
