
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

//import java.util.ArrayList;
//import java.util.Arrays;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author dragsa
// */
//public class Fast {
//
//    public static void main(String[] args) {
//
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        StdDraw.show(0);
//
//        String filename = args[0];
//        In in = new In(filename);
//        int N = in.readInt();
//        Point[] points = new Point[N];
////        double[] slopes = new double[N];
//        ArrayList<String> segments = new ArrayList<String>();
//        StdDraw.setPenRadius(0.01);
//        for (int i = 0; i < points.length; i++) {
//            points[i] = new Point(in.readInt(), in.readInt());
//            points[i].draw();
//        }
//
//        StdDraw.show(0);
//        StdDraw.setPenRadius(0.005);
//
//        for (int i = 0; i < points.length; i++) {
//            Arrays.sort(points, points[i].SLOPE_ORDER);
////            for (int z = 0; z < points.length; z++) {
////                slopes[z] = points[0].slopeTo(points[z]);
////            }
//            Point[] pointCollinearArray = new Point[2];
//            pointCollinearArray[0] = points[0];
//            int segmentCounter = 0;
//            for (int j = 1; j < points.length; j++) {
//                if (j == 1) {
//                    pointCollinearArray[1] = points[1];
//                    segmentCounter++;
//                } else if (segmentCounter == 0) {
//                    pointCollinearArray = new Point[2];
//                    pointCollinearArray[0] = points[0];
//                    pointCollinearArray[1] = points[j];
//                    segmentCounter++;
//                }
//                if (j < points.length - 1) {
//                    if (points[0].slopeTo(points[j]) == points[0].slopeTo(points[j + 1])) {
//                        pointCollinearArray = Arrays.copyOf(pointCollinearArray, pointCollinearArray.length + 1);
//                        pointCollinearArray[pointCollinearArray.length - 1] = points[j + 1];
//                        segmentCounter++;
//                    } else if (segmentCounter >= 3) {
//                        Arrays.sort(pointCollinearArray);
//                        StringBuilder builder = new StringBuilder();
//                        for (int k = 0; k < pointCollinearArray.length; k++) {
//                            if (k == pointCollinearArray.length - 1) {
//                                builder.append(pointCollinearArray[k]);
//                            } else {
//                                builder.append(pointCollinearArray[k] + " -> ");
//                            }
//                        }
//                        if (!segments.contains(builder.toString())) {
//                            segments.add(builder.toString());
//                            pointCollinearArray[0].drawTo(pointCollinearArray[pointCollinearArray.length - 1]);
//                            System.out.println(builder);
//                        }
//                        segmentCounter = 0;
//                    } else {
//                        segmentCounter = 0;
//                    }
//                } else if (segmentCounter >= 3) {
//                    Arrays.sort(pointCollinearArray);
//                    StringBuilder builder = new StringBuilder();
//                    for (int k = 0; k < pointCollinearArray.length; k++) {
//                        if (k == pointCollinearArray.length - 1) {
//                            builder.append(pointCollinearArray[k]);
//                        } else {
//                            builder.append(pointCollinearArray[k] + " -> ");
//                        }
//                    }
//                    if (!segments.contains(builder.toString())) {
//                        segments.add(builder.toString());
//                        pointCollinearArray[0].drawTo(pointCollinearArray[pointCollinearArray.length - 1]);
//                        System.out.println(builder);
//                    }
//                    segmentCounter = 0;
//                }
//            }
//        }
//        StdDraw.show(0);
//    }
//}
    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        double[] slopes = new double[n];
        ArrayList<String> segments = new ArrayList<String>();
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }

        StdDraw.setPenRadius(0.005);

        for (int i = 0; i < points.length; i++) {
            Point[] pointsCopy = points.clone();
            Arrays.sort(pointsCopy, pointsCopy[i].SLOPE_ORDER);
            for (int x = 0; x < pointsCopy.length; x++) {
                slopes[x] = pointsCopy[x].slopeTo(pointsCopy[0]);
            }
            ArrayList<Point> pointsCollinearArrayList = null;
            Point[] pointsCollinearArray = null;

            for (int j = 1; j < pointsCopy.length; j++) {
                int segmentCounter = 1;
                int maxSegmentCounter = 1;
                int jInner = j;
                while ((jInner < pointsCopy.length - 1) && (slopes[jInner] == slopes[jInner + 1])) {
                    segmentCounter++;
                    jInner++;
                    if (segmentCounter >= 3 && maxSegmentCounter < segmentCounter) {
                        maxSegmentCounter = segmentCounter;
                    }
                }
                if (maxSegmentCounter >= 3) {
//                    pointsCollinearArrayList = new ArrayList<Point>();
//                    pointsCollinearArrayList.add(pointsCopy[0]);
//                    for (int k = 0; k < maxSegmentCounter; k++) {
//                        pointsCollinearArrayList.add(pointsCopy[jInner - k]);
//                    }
                    pointsCollinearArray = new Point[maxSegmentCounter + 1];
                    pointsCollinearArray[0] = pointsCopy[0];
                    for (int k = 0; k < maxSegmentCounter; k++) {
                        pointsCollinearArray[k + 1] = pointsCopy[jInner - k];
                        Arrays.sort(pointsCollinearArray, 0, k + 2);
                    }
                }
//                if (pointsCollinearArrayList != null) {
                if (pointsCollinearArray != null) {
//                    Point[] pointsCollinearArray = pointsCollinearArrayList.toArray(new Point[pointsCollinearArrayList.size()]);
//                    Arrays.sort(pointsCollinearArray);
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
                }
                j = jInner;
            }
        }
        StdDraw.show(0);
        System.out.println("it took " + sw.elapsedTime() + " secs for fast algorithm");
    }
}
