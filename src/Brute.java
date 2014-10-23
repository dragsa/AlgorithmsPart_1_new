
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class Brute {

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

        StdDraw.setPenRadius(0.005);

        for (int i = 0; i < point.length; i++) {
            for (int j = i + 1; j < point.length; j++) {
                for (int k = j + 1; k < point.length; k++) {
                    for (int l = k + 1; l < point.length; l++) {
                        if (point[i].slopeTo(point[j]) == point[i].slopeTo(point[k])
                                && point[i].slopeTo(point[j]) == point[i].slopeTo(point[l])) {
                            Point[] array = new Point[]{point[i], point[j], point[k], point[l]};
                            Arrays.sort(array);
                            System.out.println(array[0] + " -> " + array[1] + " -> " + array[2] + " ->" + array[3]);

                            array[0].drawTo(array[3]);
                        }
                    }
                }
            }
        }
        StdDraw.show(0);
    }
}
