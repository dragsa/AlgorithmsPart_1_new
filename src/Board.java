
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
    private int[] emptyBlock = new int[2];
    private int hammingIndex;
    private int manhattanIndex;

    public Board(int[][] blocks) {
        tiles = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            tiles[i] = Arrays.copyOf(blocks[i], blocks.length);
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) {
                    emptyBlock[0] = i;
                    emptyBlock[1] = j;
                } else {
                    int chord = tiles[i][j];
                    if (chord != flatter(i, j)) {
//                    System.out.println(tiles[i][j]);
                        hammingIndex++;
                        int[] properChords = deFlatter(chord);
                        int iProper = properChords[0];
                        int jProper = properChords[1];
                        manhattanIndex += Math.abs(i - iProper) + Math.abs(j - jProper);
                    }
                }
            }
        }
//        tiles = blocks.clone();
        N = tiles.length;
    }
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    private int flatter(final int i, final int j) {
        int flatCoordinate = i * tiles.length + (j + 1);
        return flatCoordinate;
    }

    private int[] deFlatter(final int k) {
        int i = 0;
        int j = 0;
        if (k == 0) {
            return emptyBlock;
        }
        if (k > tiles.length) {
            j = (k - 1) % tiles.length;
            i = (k - j) / tiles.length;
        } else {
            j = k - 1;
            i = 0;
        }
        return new int[]{i, j};
    }

    public int dimension() {
        return N;
    }
    // board dimension N

    public int hamming() {
        return hammingIndex;
    }
    // number of blocks out of place

    public int manhattan() {
        return manhattanIndex;
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
        return s.toString();
    }
    // string representation of this board (in the output format specified below)

    public void flatterResult() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                System.out.printf("%2d ", flatter(i, j));
            }
            System.out.println("");
        }
    }
//       flat structure print

    public void deFlatterResult() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = tiles.length - 1; j < tiles.length; j++) {
                System.out.println("chords: " + i + " " + j);
                System.out.println("proper chords: " + deFlatter(tiles[i][j])[0] + " " + deFlatter(tiles[i][j])[1]);
            }
        }
    }

    public static void main(String[] args) {
    }
    // unit tests (not graded)
}
