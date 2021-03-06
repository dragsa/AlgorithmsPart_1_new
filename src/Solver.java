
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class Solver {

    private MinPQ<SearchNode> minQueueInitial;
    private MinPQ<SearchNode> minQueueTwin;
    private boolean solvable;
    private SearchNode goalNode;

    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        minQueueInitial = new MinPQ<SearchNode>();
        minQueueTwin = new MinPQ<SearchNode>();
        minQueueInitial.insert(new SearchNode(initial, null, 0));
        minQueueTwin.insert(new SearchNode(initial.twin(), null, 0));
        while (true) {
            SearchNode currentMinSearchNode = minQueueInitial.delMin();
            if (currentMinSearchNode.getBoard().isGoal()) {
                solvable = true;
                goalNode = currentMinSearchNode;
                break;
            }
            for (Board currentNeighborBoard : currentMinSearchNode.getBoard().neighbors()) {
                if (currentMinSearchNode.getPreviousNode() == null) {
                    minQueueInitial.insert(new SearchNode(currentNeighborBoard, currentMinSearchNode, 1));
//                    System.out.println("queue content START");
//                    for (SearchNode node : minQueueInitial) {
//                        System.out.print(node.getBoard());
//                    }
//                    System.out.println("queue content END");
                } else if (!currentNeighborBoard.equals(currentMinSearchNode.getPreviousNode().getBoard())) {
                    minQueueInitial.insert(new SearchNode(currentNeighborBoard, currentMinSearchNode,
                            currentMinSearchNode.getMoves() + 1));
//                    System.out.println("queue content START");
//                    for (SearchNode node : minQueueInitial) {
//                        System.out.print(node.getBoard());
//                    }
//                    System.out.println("queue content END");
                }
            }

            SearchNode currentMinSearchNodeTwin = minQueueTwin.delMin();
            if (currentMinSearchNodeTwin.getBoard().isGoal()) {
                break;
            }
            for (Board currentNeighborBoardTwin : currentMinSearchNodeTwin.getBoard().neighbors()) {
                if (currentMinSearchNodeTwin.getPreviousNode() == null) {
                    minQueueTwin.insert(new SearchNode(currentNeighborBoardTwin, currentMinSearchNodeTwin, 1));
                } else if (!currentNeighborBoardTwin.equals(currentMinSearchNodeTwin.getPreviousNode().getBoard())) {
                    minQueueTwin.insert(new SearchNode(currentNeighborBoardTwin, currentMinSearchNodeTwin,
                            currentMinSearchNodeTwin.getMoves() + 1));
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
        if (isSolvable()) {
            return goalNode.getMoves();
        }
        return -1;
    }
    // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        if (isSolvable()) {
            Stack<Board> solution = new Stack<Board>();
            SearchNode indexNode = null;
            try {
                indexNode = goalNode.clone();
            } catch (CloneNotSupportedException cnsex) {
                cnsex.printStackTrace();
            }
            while (!(indexNode.getBoard() == null)) {
                solution.push(indexNode.getBoard());
                // TO DO
                if (indexNode.getPreviousNode() == null) {
                    return solution;
                } else {
                    indexNode = new SearchNode(indexNode.getPreviousNode().getBoard(), indexNode.getPreviousNode().getPreviousNode(),
                            indexNode.getMoves() - 1);
                }
            }
        }
        return null;
    }
    // sequence of boards in a shortest solution; null if unsolvable

    private class SearchNode implements Comparable<SearchNode>, Cloneable {

        private int moves;
        private Board board;
        private SearchNode previousNode;

        public SearchNode(Board board, SearchNode previousNode, int moves) {
            this.board = board;
            this.previousNode = previousNode;
            this.moves = moves;
        }

        public int compareTo(SearchNode that) {
            if ((this.moves + this.getBoard().manhattan()) > (that.moves + that.getBoard().manhattan())) {
                return 1;
            }
            if ((this.moves + this.getBoard().manhattan()) < (that.moves + that.getBoard().manhattan())) {
                return -1;
            } else {
                return 0;
            }
        }

        public int getMoves() {
            return moves;
        }

        public Board getBoard() {
            return board;
        }

        public SearchNode getPreviousNode() {
            return previousNode;
        }

        public SearchNode clone() throws CloneNotSupportedException {
            return (SearchNode) super.clone();
        }
    }

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