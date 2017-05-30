/******************************************************************************
 *  Name: Kat Sayrs
 *
 *  Operating system: Windows 7
 *  Compiler: Javac
 *  Text editor / IDE: IntelliJ IDEA 2016.1.1
 *  Hours to complete assignment (optional): ~10
 ******************************************************************************/


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 *****************************************************************************/

 The Percolation class creates an N-by-N grid of blocked spaces. When a site is
 opened, it checks the neighboring sites (above, below, left, right) to see if
 they are open as well. If they are, they are joined using WeightedQuickUnionUF
 objects. When a site is opened, it can be full or not full. A site is full if
 it is connected to an open space in the top row.

 I used the suggested method of creating extra nodes to keep track of the top
 and bottom rows, so that the final check is just seeing if the top row and
 bottom row nodes are in the same component. In order to prevent the Backwash
 problem, I implemented a second WeightedQuickUnionUF object that did not
 include the bottom row node. This is the object that is used to check if an
 individual site is connected to the top row.

/******************************************************************************
 *  Using Percolation with QuickFindUF.java,  fill in the table below such that
 *  the N values are multiples of each other.

 *  Give a formula (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as a function of both N and T. Be sure to give both
 *  the coefficient and exponent of the leading term. Your coefficients should
 *  be based on empirical data and rounded to two significant digits, such as
 *  5.3*10^-8 * N^5.0 T^1.5.
 *****************************************************************************/

(keep T constant) = 100

 N          time (seconds)
------------------------------
20			0.0
40			0.0
80			0.1
160			17.2
320			274.8

Doubling ratio = ~11-14? Avg 13

(keep N constant) = 100

 T          time (seconds)
------------------------------
20			0.7
40			1.2
80			2.4
160			4.8
320			9.3

Doubling ratio = 2.0



running time as a function of N and T:  ~1.5*10^-9 * N^3.7 * T


/******************************************************************************
 *  Repeat the previous question, but use WeightedQuickUnionUF.java.
 *****************************************************************************/

(keep T constant) = 100

 N          time (seconds)
------------------------------
050			0.1
100			0.1
200			0.3
400			1.5
800			10.4

Doubling ratio = ~5.2


(keep N constant) = 100

 T          time (seconds)
------------------------------
050			0.2
100			0.1
200			0.2
400			0.4
800			0.7

Doubling ratio = 2.0

running time as a function of N and T:  ~1.5*10^-8 * N^2.4 * T


/**********************************************************************
 *  How much memory (in bytes) does a Percolation object use to store
 *  an N-by-N grid? Use the 64-bit memory cost model from Section 1.4
 *  of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers.
 *
 *  Include the memory for all referenced objects (deep memory).
 **********************************************************************/

Object overhead						16 bytes
booln[][] grid						N*N (booleans only take up 1 byte)
WeightedQuickUnionUF path			8N - given
WeightedQuickUnionUF topOnlyPath	8N - given
int toprow							4
int bottomrow						4
int count							4
int N								4

~ 16N + N^2

/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

Adding a second WeightedQuickUnionUF object does add some extra time to the
runtime, but ultimately it's O case should still be the same as if there were
only one WeightedQuickUnionUF.


/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/

Not necessarily serious, but I had a confusing error that put my top
and bottom row nodes within the grid space. I thought it was just a
backwash problem for a while.

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/

 The Visualizer was pretty awesome and helpful for quickly determing backwash issues.
