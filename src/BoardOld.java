
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
public class BoardOld {

    private char[][] tiles;
    private int N;
    private int emptyBlockX;
    private int emptyBlockY;
    private int hammingIndex;
    private int manhattanIndex;
    private Queue<Board> neighborsList;
    private Board twin;

    public BoardOld(int[][] blocks) {
        tiles = new char[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
//            tiles[i] = Arrays.copyOf(blocks[i], blocks.length);
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = (char) (blocks[i][j]);
                if (tiles[i][j] == 0) {
                    emptyBlockY = i;
                    emptyBlockX = j;
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
            return new int[]{emptyBlockX, emptyBlockY};
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
        return ((tiles[N - 1][N - 1] == 0) && manhattanIndex == 0);
    }
    // is this board the goal board?

    public Board twin() {
        if (twin == null) {
            int[][] tilesTwin = new int[tiles.length][tiles.length];
            for (int i = 0; i < tiles.length; i++) {
//                tilesTwin[i] = Arrays.copyOf(tiles[i], tiles.length);
                for (int j = 0; j < tiles[i].length; j++) {
                    tilesTwin[i][j] = (int) tiles[i][j];
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
        BoardOld that = (BoardOld) y;
        if (this.dimension() != that.dimension()) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            if (!Arrays.equals(this.tiles[i], that.tiles[i])) {
                return false;
            }
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
                    int[][] tilesClone = new int[tiles.length][tiles.length];
                    for (int i = 0; i < tiles.length; i++) {
//                        tilesClone[i] = Arrays.copyOf(tiles[i], tiles.length);
                        for (int j = 0; j < tiles.length; j++) {
                            tilesClone[i][j] = (int) tiles[i][j];
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
        s.append(tiles.length + "\n");
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                s.append(String.format("%2d ", (int) tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    // string representation of this board (in the output format specified below)

    private void flatterResult() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                System.out.printf("%2d ", flatter(i, j));
            }
            System.out.println("");
        }
    }

    private void deFlatterResult() {
        for (int i = 7; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                System.out.println("chords: " + i + " " + j);
                System.out.println("proper chords: " + deFlatter(tiles[i][j])[0] + " " + deFlatter(tiles[i][j])[1]);
            }
        }
    }

    public static void main(String[] args) {
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

            BoardOld initial = new BoardOld(tiles);
            System.out.println(initial);
// neighbors test
//            Iterable<Board> neib = initial.neighbors();
//            for (Board memberBoard : neib) {
//                System.out.println(memberBoard);
//            }

// flatters and functon indexes test
//            initial.flatterResult();
//            initial.deFlatterResult();
            System.out.println(initial.hamming());
            System.out.println(initial.manhattan());

//// immutability test
//            tiles[0][0] = 142;
//            System.out.println(initial);
        }
    }
}
