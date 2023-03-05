import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] box;
    private WeightedQuickUnionUF unionFinds;
    private int n;
    public static void main(String[] args) {
        Percolation p =new Percolation(5);


    }
    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        unionFinds=new WeightedQuickUnionUF(n*n+2);
        box= new boolean[n][n];
        this.n = n;
        for (int i = 1; i <= n; i++) {
            unionFinds.union(0, Dto2D(1, i));
            unionFinds.union(n*n+1, Dto2D(n, i));
        }

        //to print the overall grid values
        for(int i=0; i<(n*n+2);i++){
            System.out.printf(" "+unionFinds.find(i));
        }
    }

    // mapping function
    private int Dto2D(int row, int col){
//        System.out.printf("row "+ row);
//        System.out.printf("col "+ col);
        if(row < 1 || row > n || col < 1 || col > n){
            throw new IllegalArgumentException("rows and columns should be 1 and " +n);
        }
        return (row-1)*n + (col-1)+1;

    }
}
