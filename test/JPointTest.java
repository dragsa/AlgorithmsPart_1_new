
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class JPointTest {

    static Point[] setOfPoints;

    //execute for each test, before executing test
    @BeforeClass
    public static void before() {
        setOfPoints = new Point[5];
        setOfPoints[0] = new Point(1, 1);
        setOfPoints[1] = new Point(-1, 3);
        setOfPoints[2] = new Point(0, 3);
        setOfPoints[3] = new Point(-1, -1);
        setOfPoints[4] = new Point(0, -1);
    }

    @Test
    public void testSimpleSlopeTo1() {
        assertEquals(new Double(-1.0), new Double(setOfPoints[0].slopeTo(setOfPoints[1])));
    }
    
    @Test
    public void testSimpleSlopeTo2() {
        assertEquals(new Double(1.0), new Double(setOfPoints[0].slopeTo(setOfPoints[3])));
    }
    
    @Test
    public void testSimpleSlopeTo3() {
        assertEquals(new Double(-2.0), new Double(setOfPoints[0].slopeTo(setOfPoints[2])));
    }
    
    @Test
    public void testSimpleSlopeTo4() {
        assertEquals(new Double(2.0), new Double(setOfPoints[0].slopeTo(setOfPoints[4])));
    }
    
    @Test
    public void testPointComparator() {
        System.out.println("using slope comparator:");
        Arrays.sort(setOfPoints, setOfPoints[0].SLOPE_ORDER);
        for (int i = 0; i < setOfPoints.length; i++) {
            System.out.println(setOfPoints[i] + " ");
        }
        System.out.println("");
    }
    
    @Test
    public void testPointNaturalOrder() {
        System.out.println("using natural order:");
        Arrays.sort(setOfPoints);
        for (int i = 0; i < setOfPoints.length; i++) {
            System.out.println(setOfPoints[i] + " ");
        }
        System.out.println("");
    }
}
