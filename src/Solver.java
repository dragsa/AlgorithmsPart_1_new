
import edu.princeton.cs.algs4.MinPQ;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class Solver {

    private MinPQ<Board> minQueueInitial;
    private int initialSteps;
    private MinPQ<Board> minQueueTwin;
    private boolean solvable;

    public Solver(Board initial) {
        MinPQ<Board> minQueueInitial = new MinPQ<Board>();
        MinPQ<Board> minQueueTwin = new MinPQ<Board>();
        minQueueInitial.insert(initial);
        minQueueTwin.insert(initial.twin());
        while (true) {
            Board currentMinBoardInitial = minQueueInitial.delMin();
            if (currentMinBoardInitial.isGoal()) {
                solvable = true;
                break;
            }
            if (currentMinBoardInitial.neighbors().iterator().hasNext()) {
                initialSteps++;
            }
            for (Board currentNeighbor : currentMinBoardInitial.neighbors()) {
                if (!currentMinBoardInitial.equals(minQueueInitial.min())) {
                    minQueueInitial.insert(currentNeighbor);
                }
            }
            
            Board currentMinBoardTwin = minQueueTwin.delMin();
            if (currentMinBoardTwin.isGoal()) {
                break;
            }
            for (Board currentNeighbor : currentMinBoardTwin.neighbors()) {
                if (!currentMinBoardTwin.equals(minQueueInitial.min())) {
                    minQueueTwin.insert(currentNeighbor);
                }
            }
        }
    }
    // find a solution to the initial board (using the A* algorithm)

    public boolean isSolvable() {
        return solvable;
    }
    // is the initial board solvable?

    public int moves() {
        if (isSolvable()) return initialSteps;
        return -1;
    }
    // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        return null;
    }
    // sequence of boards in a shortest solution; null if unsolvable

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
    // solve a slider puzzle (given below)
}