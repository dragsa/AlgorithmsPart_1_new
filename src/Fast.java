
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
        Point[] point = new Point[N];
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < point.length; i++) {
            point[i] = new Point(in.readInt(), in.readInt());
            point[i].draw();
        }

        StdDraw.show(0);
        StdDraw.setPenRadius(0.005);

        for (int i = 0; i < point.length; i++) {
            Arrays.sort(point, point[i].SLOPE_ORDER);
            Point[] pointList = new Point[2];
            pointList[0] = point[0];
            pointList[1] = point[1];
            int segmentCounter = 1;
            for (int j = 1; j < point.length; j++) {
                if (j != point.length - 1) {
                    if (point[0].slopeTo(point[j]) == point[0].slopeTo(point[j + 1])) {
                        pointList = Arrays.copyOf(pointList, pointList.length + 1);
                        pointList[pointList.length - 1] = point[j + 1];
                        segmentCounter++;
                    } else if (segmentCounter >= 3) {
                        Arrays.sort(pointList);
                        point[0].drawTo(pointList[pointList.length - 1]);
                        segmentCounter = 1;
                        StdDraw.show(0);
                    }
                } else if (segmentCounter >= 3) {
                    Arrays.sort(pointList);
                    point[0].drawTo(pointList[pointList.length - 1]);
                    segmentCounter = 1;
                    StdDraw.show(0);
                }
            }
        }
        StdDraw.show(0);
    }
}
