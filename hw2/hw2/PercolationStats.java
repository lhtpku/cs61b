package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] res;
    private int testNum;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        res = new double[T];
        int squareN = N * N;
        testNum = T;

        for (int i = 0; i < T; i++) {
            Percolation sample = pf.make(N);
            while (!sample.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                sample.open(row, col);
            }
            res[i] = (float) sample.numberOfOpenSites() / squareN;
        }
    }

    public double mean() {
        return StdStats.mean(res);
    }

    public double stddev() {
        return StdStats.stddev(res);
    }

    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(testNum));
    }

    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(testNum));
    }

    public static void main(String[] args) {
        PercolationStats tmp = new PercolationStats(568, 401, new PercolationFactory());
        System.out.println(tmp.mean());
        System.out.println(tmp.stddev());
        System.out.println(tmp.confidenceLow());
        System.out.println(tmp.confidenceHigh());
    }
}
