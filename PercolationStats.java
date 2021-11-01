/* *****************************************************************************
 *  Name:              SuHong Park
 *  Coursera User ID:  4kidsp@naver.com
 *  Last modified:     October 15, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] threshold;
    private final int trialNum;
    private double mean, stddev;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("PercolationStats(int n, int trials): n <= 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("PercolationStats(int n, int trials): trials <= 0");
        }
        // Stopwatch time = new Stopwatch();
        threshold = new double[trials];
        trialNum = trials;
        int size = n * n;
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                test.open(row, col);
            }
            threshold[i] = (double) test.numberOfOpenSites() / (double) size;
        }
        // StdOut.println(time.elapsedTime());
        mean = mean();
        stddev = stddev();
    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(threshold);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(threshold);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (CONFIDENCE_95 * stddev / Math.sqrt(trialNum));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (CONFIDENCE_95 * stddev / Math.sqrt(trialNum));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        String meanS = "mean";
        String stddevS = "stddev";
        String confidenceS = "95% confidence interval";
        StdOut.printf("%-25s = %f\n", meanS, stats.mean());
        StdOut.printf("%-25s = %f\n", stddevS, stats.stddev());
        StdOut.printf("%-25s = [%f, %f]\n", confidenceS, stats.confidenceLo(),
                      stats.confidenceHi());
    }

}
