import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by Katyana on 4/13/2016.
 *
 * For showing doubling ratios.
 */

public class TestClient {

    public static double time(int N, int T){

        Stopwatch timer = new Stopwatch();
        PercolationStats noot = new PercolationStats(N, T);
        return timer.elapsedTime();
        /*StdOut.println("mean(): " + noot.mean());
        StdOut.println("stddev(): " + noot.stddev());
        StdOut.println("confidenceLow: " + noot.confidenceLow());
        StdOut.println("confidenceHigh: " + noot.confidenceHigh());*/
    }

    public static void main(String[] args){
        // Print table of running times.
        // N = size of grid
        // T = number of trials
        int T = 100;
        int N = 50;
        double prev = 1.0;
        for (int i = 0; i<10; i++) {
            // Print time for problem size N.
            double time = time(N, T);
            StdOut.printf("%5.1f\n", time/prev);
            StdOut.printf("%7d %5.1f\n", N, time); //Change to N or T depending on what test is being run
            N += N;
            prev = time;
            //T += T;
        }
    }
}
