
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
        Stopwatch sw = new Stopwatch();

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        ArrayList<String> segments = new ArrayList<String>();
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }

        StdDraw.setPenRadius(0.005);

        for (int i = 0; i < points.length; i++) {
//            Point[] pointsCopy = Arrays.copyOf(points, points.length);
            Point[] pointsCopy = points.clone();
            Arrays.sort(pointsCopy, pointsCopy[i].SLOPE_ORDER);
            Point[] pointsCollinearArray = new Point[2];
            pointsCollinearArray[0] = pointsCopy[0];
            int segmentCounter = 0;
            for (int j = 1; j < pointsCopy.length; j++) {
                if (j == 1) {
                    pointsCollinearArray[1] = pointsCopy[1];
                    segmentCounter++;
                } else if (segmentCounter == 0) {
                    pointsCollinearArray = new Point[2];
                    pointsCollinearArray[0] = pointsCopy[0];
                    pointsCollinearArray[1] = pointsCopy[j];
                    segmentCounter++;
                }
                if (j < pointsCopy.length - 1) {
                    if (pointsCopy[0].slopeTo(pointsCopy[j]) == pointsCopy[0].slopeTo(pointsCopy[j + 1])) {
                        pointsCollinearArray = Arrays.copyOf(pointsCollinearArray, pointsCollinearArray.length + 1);
                        pointsCollinearArray[pointsCollinearArray.length - 1] = pointsCopy[j + 1];
                        segmentCounter++;
                    } else if (segmentCounter >= 3) {
                        Arrays.sort(pointsCollinearArray);
                        StringBuilder builder = new StringBuilder();
                        for (int k = 0; k < pointsCollinearArray.length; k++) {
                            if (k == pointsCollinearArray.length - 1) {
                                builder.append(pointsCollinearArray[k]);
                            } else {
                                builder.append(pointsCollinearArray[k] + " -> ");
                            }
                        }
                        if (!segments.contains(builder.toString())) {
                            segments.add(builder.toString());
                            pointsCollinearArray[0].drawTo(pointsCollinearArray[pointsCollinearArray.length - 1]);
                            System.out.println(builder);
                        }
                        segmentCounter = 0;
                    } else {
                        segmentCounter = 0;
                    }
                } else if (segmentCounter >= 3) {
                    Arrays.sort(pointsCollinearArray);
                    StringBuilder builder = new StringBuilder();
                    for (int k = 0; k < pointsCollinearArray.length; k++) {
                        if (k == pointsCollinearArray.length - 1) {
                            builder.append(pointsCollinearArray[k]);
                        } else {
                            builder.append(pointsCollinearArray[k] + " -> ");
                        }
                    }
                    if (!segments.contains(builder.toString())) {
                        segments.add(builder.toString());
                        pointsCollinearArray[0].drawTo(pointsCollinearArray[pointsCollinearArray.length - 1]);
                        System.out.println(builder);
                    }
                    segmentCounter = 0;
                }
            }
        }
        StdDraw.show(0);
        System.out.println("it took " + sw.elapsedTime() + " secs for fast algorithm");
        System.out.println("new one ebat`, sveta est`");
    }
}
