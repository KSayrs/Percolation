import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Katyana on 4/12/2016.
 *
 * Calculates stats of T independent experiments on an N-by-N grid using the Percolation class.
 */

public class PercolationStats {

    private int T;
    private double[] percolationThreshold;
    private double[] mean;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        if(N<=0 || T<=0){
            throw new java.lang.IllegalArgumentException("T and N cannot be negative");
        }
        this.T = T;
        this.percolationThreshold = new double[T];
        this.mean = new double[T];

        for(int i=0;i<T;i++){
            Percolation grid = new Percolation(N);
            while (!grid.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                grid.open(row, col);
            }
            percolationThreshold[i] = (double)grid.numberOfOpenSites()/(double)(N*N);
        } // for
    }

    // sample mean of percolation
    public double mean(){
        return StdStats.mean(percolationThreshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(percolationThreshold);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLow(){
        return (mean()-((1.96*stddev())/Math.sqrt(T)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return (mean()+((1.96*stddev())/Math.sqrt(T)));
    }

    // test client
    public static void main(String[] args){
        int N = 100;
        int T = 100;
        PercolationStats noot = new PercolationStats(N, T);
        StdOut.println("mean(): " + noot.mean());
        StdOut.println("stddev(): " + noot.stddev());
        StdOut.println("confidenceLow: " + noot.confidenceLow());
        StdOut.println("confidenceHigh: " + noot.confidenceHigh());
    }
}