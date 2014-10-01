/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dragsa
 */
public class Percolation {

    private int dimension;
    private byte[][] grid;
    private WeightedQuickUnionUF wquufInstanceReal;
    private WeightedQuickUnionUF wquufInstanceFake;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        dimension = N;
        wquufInstanceReal = new WeightedQuickUnionUF(N * N + 2);
        wquufInstanceFake = new WeightedQuickUnionUF(N * N + 2);
        grid = new byte[N + 1][N + 1];
    }
    // create N-by-N grid, with all sites blocked

    private void printGrid() {
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                if (j == dimension) {
                    System.out.println(grid[i][j]);
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
        }
    }

    private boolean indexCheck(int i, int j) {
        return (i >= 1 && i <= dimension && j >= 1 && j <= dimension);
    }

    private int flatter(int i, int j) {
        return j + (dimension * i - dimension);
    }

    public void open(int i, int j) {
        if (!indexCheck(i, j)) {
            throw new IndexOutOfBoundsException("grid element is out of bounds");
        }
        if (!isOpen(i, j)) {
            grid[i][j] = 1;
            if (i == 1) {
                wquufInstanceReal.union(flatter(i, j), 0);
                wquufInstanceFake.union(flatter(i, j), 0);
            }
            if (i == dimension) {
                wquufInstanceFake.union(flatter(i, j), dimension * dimension + 1);
            }
            if (indexCheck(i - 1, j) && isOpen(i - 1, j)) {
                wquufInstanceReal.union(flatter(i, j), flatter(i - 1, j));
                wquufInstanceFake.union(flatter(i, j), flatter(i - 1, j));
            }
            if (indexCheck(i + 1, j) && isOpen(i + 1, j)) {
                wquufInstanceReal.union(flatter(i, j), flatter(i + 1, j));
                wquufInstanceFake.union(flatter(i, j), flatter(i + 1, j));
            }
            if (indexCheck(i, j - 1) && isOpen(i, j - 1)) {
                wquufInstanceReal.union(flatter(i, j), flatter(i, j - 1));
                wquufInstanceFake.union(flatter(i, j), flatter(i, j - 1));
            }
            if (indexCheck(i, j + 1) && isOpen(i, j + 1)) {
                wquufInstanceReal.union(flatter(i, j), flatter(i, j + 1));
                wquufInstanceFake.union(flatter(i, j), flatter(i, j + 1));
            }
        }
    }
    // open site (row i, column j) if it is not already

    public boolean isOpen(int i, int j) {
        if (!indexCheck(i, j)) {
            throw new IndexOutOfBoundsException("grid element is out of bounds");
        }
        return grid[i][j] == 1;
    }
    // is site (row i, column j) open?

    public boolean isFull(int i, int j) {
        if (!indexCheck(i, j)) {
            throw new IndexOutOfBoundsException("grid element is out of bounds");
        }
        if (isOpen(i, j) && i == 1) {
            return true;
        }
        return wquufInstanceReal.connected(flatter(i, j), 0);
    }
    // is site (row i, column j) full?

    public boolean percolates() {
        if (dimension * dimension == 1) {
            return isOpen(1, 1);
        }
        return wquufInstanceFake.connected(0, dimension * dimension + 1);
    }
    // does the system percolate?

    public static void main(String[] args) {
        int testN = 7;
        Percolation testPercolation = new Percolation(testN);
        //print grid test
        System.out.println("initial grid:\n");
        testPercolation.printGrid();
        System.out.println();
        System.out.println("neighbour opening test:\n");
        testPercolation.open(2, 1);
        testPercolation.open(2, 2);
        testPercolation.printGrid();
        System.out.println();
        System.out.println("21 and 22 are connected: "
                + testPercolation.wquufInstanceReal.connected(testPercolation.flatter(2, 1), testPercolation.flatter(2, 2)));
        //first row test
        System.out.println();
        System.out.println("first row test:\n");
        System.out.println("11 is opened: " + testPercolation.isOpen(1, 1) + " and full: " 
                + testPercolation.isFull(1, 1));
        testPercolation.open(1, 1);
        System.out.println("11 is opened: " + testPercolation.isOpen(2, 2) + " and full: " 
                + testPercolation.isFull(1, 1));
        System.out.println();
        testPercolation.printGrid();
        System.out.println();
        System.out.println("11 and 22 are connected: "
                + testPercolation.wquufInstanceReal.connected(testPercolation.flatter(1, 1), testPercolation.flatter(2, 2)));
        System.out.println("22 is opened: " + testPercolation.isOpen(2, 2) + " and full: "
                + testPercolation.isFull(2, 2));
        System.out.println("22 is connected to artificial START: "
                + testPercolation.wquufInstanceReal.connected(testPercolation.flatter(2, 2), 0));
        System.out.println();
        System.out.println("last row test:\n");
        System.out.println("75 is opened: " + testPercolation.isOpen(7, 5) + " and full: " 
                + testPercolation.isFull(7, 5));
        //simle percolation forcing
//        testPercolation.open(1, 5);
//        testPercolation.open(2, 5);
//        testPercolation.open(3, 5);
//        testPercolation.open(4, 5);
//        testPercolation.open(5, 5);
//        testPercolation.open(6, 5);
        testPercolation.open(7, 5);
        System.out.println("75 is opened: " + testPercolation.isOpen(7, 5) + " and full: " 
                + testPercolation.isFull(7, 5));
        System.out.println();
        testPercolation.printGrid();
        System.out.println();
        System.out.println("75 is connected to artificial END: "
                + testPercolation.wquufInstanceReal.connected(testPercolation.flatter(7, 5), testN * testN + 1));
        System.out.println();
        System.out.println("system percolates?: "
                + testPercolation.percolates());
        System.out.println();
        //wquf test
//        for (int i =0; i < testPercolation.wqufInstance.count(); i++) {
//            System.out.print(testPercolation.wqufInstance.find(i) + " ");
//        }
//        System.out.println("\n");
        //flatter test
//        System.out.println("");
//        System.out.println("flatter test:\n");
//        for (int i = 1; i <= testN; i++) {
//            for (int j = 1; j <= testN; j++) {
//               if (j == testN) {
//                    System.out.println(testPercolation.flatter(i, j));
//                }
//                else System.out.print(testPercolation.flatter(i, j) + " ");
//            }
//        }
    }
    // test client, optional
}
