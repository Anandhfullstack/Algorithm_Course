import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private WeightedQuickUnionUF wQuickFindGrid;
    private WeightedQuickUnionUF wQuickFindFull;
    private int gridSize;
    private int gridSquared;
    private int vTop;
    private int vBottom;
    private int openSitesCount;
    private boolean[][] box;
    private WeightedQuickUnionUF unionFinds;

    public static void main(String[] args) {
        Percolation p =new Percolation(5);


    }
    public Percolation(int n){
        if (n <= 0) { throw new IllegalArgumentException("n must be greater than 0");}
        gridSize = n;
        gridSquared = n*n;
        box= new boolean[gridSize][gridSize];
        wQuickFindGrid= new WeightedQuickUnionUF(gridSquared+2);
        wQuickFindFull=new WeightedQuickUnionUF(gridSquared+1);
        vTop=gridSquared;
        vBottom=gridSquared;
        openSitesCount =0;

    }

}
