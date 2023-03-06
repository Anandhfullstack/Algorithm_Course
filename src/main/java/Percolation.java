import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private WeightedQuickUnionUF wQuickFindBox;
    private WeightedQuickUnionUF wQuickFindFull;
    private int boxSize;
    private int boxSquared;
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
        boxSize = n;
        boxSquared = n*n;
        box= new boolean[boxSize][boxSize];
        wQuickFindBox= new WeightedQuickUnionUF(boxSquared+2);
        wQuickFindFull= new WeightedQuickUnionUF(boxSquared+1);
        vTop=boxSquared;
        vBottom=boxSquared;
        openSitesCount =0;

    }

    public boolean isOpen(int row, int col){
        return box[row][col];
    }

}
