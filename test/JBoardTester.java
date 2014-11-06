
import java.io.File;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agnatyuk
 */
public class JBoardTester {

    static Board boardInstance;

    //execute for each test, before executing test
    @BeforeClass
    public static void before() {
//        In in = new In(new File("C:\\Users\\agnatyuk\\Documents\\NetBeansProjects\\AlgorithmsPart_1_new\\src\\puzzle00.txt"));
        In in = new In(new File("C:\\Users\\dragsa\\Documents\\NetBeansProjects\\Algorithms_Part_1\\src\\puzzle10.txt"));
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        // solve the slider puzzle
        boardInstance = new Board(tiles);
    }

    @Test
    public void testHamming() {
        assertEquals(boardInstance.hamming(), 0);
    }

    @Test
    public void testManhatten() {
        assertEquals(boardInstance.manhattan(), 0);
    }

    @Test
    public void testIsGoal() {
        assertEquals(boardInstance.isGoal(), true);
    }
}
