// import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Katyana on 4/12/2016.
 *
 * Percolation Class
 *
 * Creates an N-by-N grid of blocked spaces. When a space is opened, it can be full or not full.
 * An open space is full if it is connected to an open space in the top row.
 *
 * A grid percolates when there is a path of connected open squares that goes from the top row
 * to the bottom row. This class uses two Weighted Quick Union paths to determine whether or not
 * a system percolates.
 *
 */

public class Percolation {

    private boolean grid[][];
    private WeightedQuickUnionUF path;
   // private QuickFindUF path;
    private WeightedQuickUnionUF topOnlyPath;
    //private QuickUnionUF topOnlyPath;
    private int toprow;
    private int bottomrow;
    private int count = 0;
    private int N;

    // create N-by-N grid, with all sites initially blocked
    /**
     * Creates N-by-N grid, with all sites initially blocked.
     * Also initializes two WeightedQuickUnionUF objects: one of size N*N+2 to keep track of top
     * and bottom nodes, the other of size N*N+1 to keep track of the top node only.
     * */
    public Percolation(int N){
        //StdOut.println(N);
        if (N<=0){
            throw new java.lang.IllegalArgumentException("N cannot be <=0.");
        }
        this.N = N;
        path = new WeightedQuickUnionUF(N*N+2); //accommodating for extra nodes for top and bottom row
        //path = new QuickFindUF(N*N+2);
        topOnlyPath = new WeightedQuickUnionUF(N*N+1);
        //topOnlyPath = new QuickUnionUF(N*N+1);
        grid = new boolean[N][N]; // initializes to false by default
        toprow = N*N;
        bottomrow = N*N+1;
    }

    // open the site (row, col) if it is not open already
    /**
     * Opens the site (row, col) if it is not open already. Then checks to see if surrounding sites are open and
     * connects them.
     * */
    public void open(int row, int col){
        if (row <0 || row > N-1 || col<0 || col>N-1){
            throw new java.lang.IndexOutOfBoundsException("Nonexistent space.");
        }

        if (isOpen(row, col)) { return; } // already open

        grid[row][col] = true; //opens
        count++; //counting open sites

        // top virtual node
        if (row == 0){
            path.union(toprow, getPathLocation(row, col));
            topOnlyPath.union(toprow, getPathLocation(row, col));
        }
        // bottom virtual node
        if(row == N-1){
            path.union(bottomrow, getPathLocation(row, col));
        }

        //check previous
        if(col-1>=0 && isOpen(row, col-1)){
            path.union(getPathLocation(row, col), getPathLocation(row, col-1));
            topOnlyPath.union(getPathLocation(row, col), getPathLocation(row, col-1));
        }
        //check above
        if(row-1>=0 && isOpen(row-1, col)){
            path.union(getPathLocation(row, col), getPathLocation(row-1, col));
            topOnlyPath.union(getPathLocation(row, col), getPathLocation(row-1, col));
        }

        //check next
        if(col+1<=N-1 && isOpen(row, col+1)){
            path.union(getPathLocation(row, col), getPathLocation(row, col+1));
            topOnlyPath.union(getPathLocation(row, col), getPathLocation(row, col+1));
        }

        //check below
        if(row+1<=N-1 && isOpen(row+1, col)){
            path.union(getPathLocation(row, col), getPathLocation(row+1, col));
            topOnlyPath.union(getPathLocation(row, col), getPathLocation(row+1, col));
        }
    } // open

    // gets the location in the UF of an individual spot
    private int getPathLocation(int row, int col){
        return (N)*(row) + (col);
    }

    // is the site (row, col) open? True = open
    /**
     * Returns true is the site is open.
     * */
    public boolean isOpen(int row, int col){
        if (row <0 || row>N-1 || col<0 || col>N-1){
            throw new java.lang.IndexOutOfBoundsException("Nonexistent space.");
        }
        return grid[row][col];
    }
    /**
     * Returns true is the site is full.
     * */
    // is the site (row, col) full? true = yes
    public boolean isFull(int row, int col){
        if (row <0 || row>N || col<0 || col>N-1){
            throw new java.lang.IndexOutOfBoundsException("Nonexistent space.");
        }
        return topOnlyPath.connected(getPathLocation(row, col), toprow); //full things are connected to the toprow node
    }

    /**
     * Returns the number of open sites.
     * */
    // number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    /**
     * Returns true if the system percolates.
     * */
    // does the system percolate?
    public boolean percolates(){
        return path.connected(toprow, bottomrow);
    }

    // unit testing (required)
    public static void main(String[] args){

        // tiny hardwired test client
        Percolation test = new Percolation(5);
        test.open(0,0);
        test.open(1,0);
        test.open(2,0);
        test.open(3,0);
        //test.open(4,2); //no percolate
        test.open(4,0); //yes percolate

        // random test
        /* Percolation test = new Percolation(100);
        while (!test.percolates()) {
            int row = StdRandom.uniform(0, 10);
            int col = StdRandom.uniform(0, 10);
            test.open(row, col);
        } */

        StdOut.println("How many open sites? " + test.numberOfOpenSites());
        StdOut.println("Is (0,0) open? " + test.isOpen(0,0)); //yes
        StdOut.println("Is (2,2) open? " + test.isOpen(2,2)); //no
        StdOut.println("Is (0,0) Full? " + test.isFull(0,0)); //yes
        StdOut.println("Is (4,2) Full? " + test.isFull(4,2)); //no
        StdOut.println("Does it percolate? " + test.percolates());
    }
}