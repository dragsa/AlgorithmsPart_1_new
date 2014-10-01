/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dragsa
 */
public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        double[] fractionArray = new double[T];
        for (int k = 0; k < T; k++) {
            Percolation percolationInstance = new Percolation(N);
            double openCounter = 0;
            while (!percolationInstance.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!percolationInstance.isOpen(i, j)) {
                    percolationInstance.open(i, j);
                    openCounter++;
                }
            }
            fractionArray[k] = openCounter / (N * N);
        }
//        mean = StdStats.sum(fractionArray) / T;
//        double tempStddev = 0.0;
//        for (int k = 0; k < T; k++) {
//            tempStddev += (fractionArray[k] - mean) * (fractionArray[k] - mean);
//        }
//        stddev = Math.sqrt(tempStddev / (T - 1));
        mean = StdStats.mean(fractionArray);
        stddev = StdStats.stddev(fractionArray);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(T);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(T);
    }
    // perform T independent computational experiments on an N-by-N grid

    public double mean() {
        return mean;
    }
    // sample mean of percolation threshold

    public double stddev() {
        return stddev;
    }
    // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return confidenceLo;
    }
    // returns lower bound of the 95% confidence interval

    public double confidenceHi() {
        return confidenceHi;
    }
    // returns upper bound of the 95% confidence interval

    public static void main(String[] args) {
        PercolationStats percolationStatsInstance = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + percolationStatsInstance.mean());
        System.out.println("stddev                  = " + percolationStatsInstance.stddev());
        System.out.println("95% confidence interval = " + percolationStatsInstance.confidenceLo() + ", "
                + percolationStatsInstance.confidenceHi());
    }
    // test client, described below
}