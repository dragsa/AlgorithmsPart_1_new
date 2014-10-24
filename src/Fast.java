
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class Fast {

    public static void main(String[] args) {

        StdDraw.setXscale(0, 20);
        StdDraw.setYscale(0, 20);
        StdDraw.show(0);

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        double[] slopes = new double[N];
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }

        StdDraw.show(0);
        StdDraw.setPenRadius(0.005);

        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, points[i].SLOPE_ORDER);
            for (int z = 0; z < points.length; z++) {
                slopes[z] = points[0].slopeTo(points[z]);
            }
            Point[] pointCollinearArray = new Point[2];
            pointCollinearArray[0] = points[0];
            int segmentCounter = 0;
            for (int j = 1; j < points.length; j++) {
                if (j == 1) {
                    pointCollinearArray[1] = points[1];
                    segmentCounter++;
                } else if (segmentCounter == 0) {
                    pointCollinearArray = new Point[2];
                    pointCollinearArray[0] = points[0];
                    pointCollinearArray[1] = points[j];
                    segmentCounter++;
                }
                if (j < points.length - 1) {
                    if (points[0].slopeTo(points[j]) == points[0].slopeTo(points[j + 1])) {
                        pointCollinearArray = Arrays.copyOf(pointCollinearArray, pointCollinearArray.length + 1);
                        pointCollinearArray[pointCollinearArray.length - 1] = points[j + 1];
                        segmentCounter++;
                    } else if (segmentCounter >= 3) {
                        Arrays.sort(pointCollinearArray);
                        pointCollinearArray[0].drawTo(pointCollinearArray[pointCollinearArray.length - 1]);
                        segmentCounter = 0;
                    } else {
                        segmentCounter = 0;
                    }
                } else if (segmentCounter >= 3) {
                    Arrays.sort(pointCollinearArray);
                    pointCollinearArray[0].drawTo(pointCollinearArray[pointCollinearArray.length - 1]);
                    segmentCounter = 0;
                }
            }
        }
        StdDraw.show(0);
    }
}
