
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class Board {

    private int[][] tiles;
    private int N;

    public Board(int[][] blocks) {
        tiles = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            tiles[i] = Arrays.copyOf(blocks[i], blocks.length);
        }
//        tiles = blocks.clone();
        N = tiles.length * tiles.length;
    }
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    private int flatter(final int i, final int j) {
        int flatCoordinate = (i + j * tiles.length);
        return flatCoordinate;
    }

    public int dimension() {
        return N;
    }
    // board dimension N

    public int hamming() {
        return 0;
    }
    // number of blocks out of place

    public int manhattan() {
        return 0;
    }
    // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return false;
    }
    // is this board the goal board?

    public Board twin() {
        return null;
    }
    // a board that is obtained by exchanging two adjacent blocks in the same row

    public boolean equals(Object y) {
        return false;
    }
    // does this board equal y?

    public Iterable<Board> neighbors() {
        return null;
    }
    // all neighboring boards

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(tiles.length + "\n");
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        //       flat structure print
        return s.toString();
    }
    
    public void flaterResult() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                System.out.print(flatter(j, i) + " ");
            }
            System.out.println("");
        }
    }
    // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
    }
    // unit tests (not graded)
}
