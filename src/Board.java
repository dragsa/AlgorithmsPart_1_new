
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class Board {

    private char[] tiles;
    private int N;
    private int emptyBlockIndex;
    private int emptyBlockX;
    private int emptyBlockY;
    private int hammingIndex;
    private int manhattanIndex;
    private Queue<Board> neighborsList;
    private Board twin;

    public Board(int[][] blocks) {
        tiles = new char[blocks.length * blocks.length];
        N = blocks.length;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                int flatIndex = flatter(i, j);
                tiles[flatIndex] = (char) (blocks[i][j]);
                if (tiles[flatIndex] == 0) {
                    emptyBlockIndex = flatIndex;
                    emptyBlockY = deFlatter(emptyBlockIndex)[0];
                    emptyBlockX = deFlatter(emptyBlockIndex)[1];
                } else if ((int)tiles[flatIndex] != flatIndex + 1) {
                    hammingIndex++;
                    int[] properChords = deFlatter((int) tiles[flatIndex] - 1);
                    int iProper = properChords[0];
                    int jProper = properChords[1];
                    manhattanIndex += Math.abs(i - iProper) + Math.abs(j - jProper);
                }
            }
        }
    }
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    private int flatter(final int i, final int j) {
        return i * N + j;
    }

    private int[] deFlatter(final int k) {
        int i = 0;
        int j = 0;
        if (k == 0) {
            return new int[]{0, 0};
        }
        if (k < N) {
            j = k - 1;
            i = 0;
        } else {
            i = k / N;
            j = k - i * N;
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
        return ((tiles[N * N - 1] == 0) && manhattanIndex == 0);
    }
    // is this board the goal board?

    public Board twin() {
        if (twin == null) {
            int[][] tilesTwin = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tilesTwin[i][j] = (int) tiles[flatter(i, j)];
                }
            }
            int indexI;
            if (emptyBlockY == 0) {
                indexI = 1;
            } else {
                indexI = StdRandom.uniform(0, emptyBlockY);
            }
            int indexJ = StdRandom.uniform(0, tilesTwin.length - 1);
            int valueAtIndexA = tilesTwin[indexI][indexJ];
            int valueAtIndexB = tilesTwin[indexI][indexJ + 1];
            tilesTwin[indexI][indexJ] = valueAtIndexB;
            tilesTwin[indexI][indexJ + 1] = valueAtIndexA;
            twin = new Board(tilesTwin);
            return twin;
        } else {
            return twin;
        }
    }
    // a board that is obtained by exchanging two adjacent blocks in the same row

    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) {
            return false;
        }
        if (!Arrays.equals(this.tiles, that.tiles)) {
            return false;
        }
        return true;
    }
    // does this board equal y?

    public Iterable<Board> neighbors() {
        if (neighborsList == null) {
            neighborsList = new Queue<Board>();
            List<int[]> blocksToMoveList = neighborsHelper();
//            for (int[] member : blocksToMoveList) {
//                System.out.println(Arrays.toString(member));
//            }
            if (blocksToMoveList.size() > 0) {
                for (int[] member : blocksToMoveList) {
                    int[][] tilesClone = new int[N][N];
                    for (int i = 0; i < tilesClone.length; i++) {
                        for (int j = 0; j < tilesClone.length; j++) {
                            tilesClone[i][j] = (int) tiles[flatter(i, j)];
                        }
                    }
                    tilesClone[emptyBlockY][emptyBlockX] = tilesClone[member[0]][member[1]];
                    tilesClone[member[0]][member[1]] = 0;
                    Board tempBoard = new Board(tilesClone);
                    neighborsList.enqueue(tempBoard);
                }
            }
            return neighborsList;
        } else {
            return neighborsList;
        }
    }
    // all neighboring boards

    private List<int[]> neighborsHelper() {
        List<int[]> blocksToMoveList = new ArrayList<int[]>();
        int[] deltaX = new int[]{-1, 0, 1};
        int[] deltaY = new int[]{-1, 0, 1};
        for (int currentDeltaY : deltaY) {
            for (int currentDeltaX : deltaX) {
                if (Math.abs(currentDeltaX) != Math.abs(currentDeltaY)) {
                    if (0 <= emptyBlockY + currentDeltaY && N > emptyBlockY + currentDeltaY
                            && 0 <= emptyBlockX + currentDeltaX && N > emptyBlockX + currentDeltaX) {
                        int[] blockToMove = new int[]{emptyBlockY + currentDeltaY, emptyBlockX + currentDeltaX};
//                        int[] newEmptyBlock = new int[]{emptyBlock[0] + currentDeltaY, emptyBlock[1] + currentDeltaX};
//                        int[] newNeighborBlock = new int[]{emptyBlock[0], emptyBlock[1]};
//                        System.out.println("move:");
//                        System.out.println("new empty chords: " + newEmptyBlock[0] + " " + newEmptyBlock[1]);
//                        System.out.println("block to move: " + tiles[newEmptyBlock[0]][newEmptyBlock[1]]);
//                        System.out.println("new neighbor chords: " + newNeighborBlock[0] + " " + newNeighborBlock[1]);
                        blocksToMoveList.add(blockToMove);
                    }
                }
            }
        }
        return blocksToMoveList;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", (int) tiles[flatter(i, j)]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    // string representation of this board (in the output format specified below)

    private void flatterResult() {
        System.out.println("flatter result:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%2d ", flatter(i, j));
            }
            System.out.println("");
        }
    }

    private void deFlatterResult() {
        System.out.println("deflatter result:");
        for (int i = 3; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                int flatIndex = flatter(i, j);
                System.out.println("block: " + (int) tiles[flatIndex]);
                System.out.println("1D chord: " + flatIndex);
                System.out.println("2D chord: " + deFlatter(flatIndex)[0] + " "
                        + deFlatter(flatIndex)[1]);
                if ((int) tiles[flatIndex] != flatIndex + 1) {
                    int[] properChords = deFlatter((int) tiles[flatIndex] - 1);
                    System.out.println("proper chords: " + properChords[0] + " " + properChords[1]);
                }
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(9 / 5);
// for each command-line argument
        for (String filename : args) {

// read in the board specified in the filename
            In in = new In(filename);
            int N = in.readInt();
            int[][] tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            Board initial = new Board(tiles);
            System.out.println(initial);
// neighbors test
            Iterable<Board> neib = initial.neighbors();
            for (Board memberBoard : neib) {
                System.out.println(memberBoard);
            }

// flatters and functon indexes test
            initial.flatterResult();
            System.out.println("initial:");
            System.out.println(initial);
            initial.deFlatterResult();
//            System.out.println(initial.hamming());
//            System.out.println(initial.manhattan());

//// immutability test
//            tiles[0][0] = 142;
//            System.out.println(initial);
        }
    }
}
