//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
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
        int size = Integer.parseInt(args[0]);

        Percolation percolation = new Percolation(size);
        int argCount = args.length;
        for (int i = 1; argCount >= 2; i += 2) {
            int row = Integer.parseInt(args[i]);
            int col = Integer.parseInt(args[i + 1]);
            System.out.printf("Adding row: %d  col: %d %n", row, col);
            percolation.open(row, col);
            if (percolation.percolates()) {
                System.out.printf("%nThe System percolates %n");
            }
            argCount -= 2;
        }
        if (!percolation.percolates()) {
            System.out.print("Does not percolate %n");
        }

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
//        for(int i=0; i < box.length; i++){
//            for(int j=0; j<box.length; j++){
//                System.out.printf("---"+i+"----"+j);
//                System.out.printf(" "+box[i][j]);
//                System.out.println("");
//
//            }
//        }



    }

    public boolean isOpen(int row, int col){
        validateSite(row, col);
        return box[row-1][col-1];
    }
    public int numberOfOpenSites(){
        return openSitesCount;
    }
//    public void open(int row, int col){
//        if(!isOpen(row,col)){
//            box[row][col]=true;
//            if(col-1 > 0){
//                box[row][col-1]
//            }
//        }
//    }

    private void validateSite(int row, int col) {
        if (!isOnGrid(row, col)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }
    private boolean isOnGrid(int row, int col) {
        int shiftRow = row - 1;
        int shiftCol = col - 1;
        return (shiftRow >= 0 && shiftCol >= 0 && shiftRow < boxSize && shiftCol < boxSize);
    }
    public void open(int row, int col) {
        validateSite(row, col);

        int shiftRow = row - 1;
        int shiftCol = col - 1;
        int flatIndex = flattenGrid(row, col) - 1;

        // If already open, stop
        if (isOpen(row, col)) {
            return;
        }

        // Open Site

        box[shiftRow][shiftCol] = true;
        openSitesCount++;

        if (row == 1) {  // Top Row
            wQuickFindBox.union(vTop, flatIndex);
            wQuickFindFull.union(vTop, flatIndex);
        }

        if (row == boxSize) {  // Bottom Row
            wQuickFindBox.union(vBottom, flatIndex);
        }

        // Check and Open Left
        if (isOnGrid(row, col - 1) && isOpen(row, col - 1)) {
            wQuickFindBox.union(flatIndex, flattenGrid(row, col - 1) - 1);
            wQuickFindFull.union(flatIndex, flattenGrid(row, col - 1) - 1);
        }

        // Check and Open Right
        if (isOnGrid(row, col + 1) && isOpen(row, col + 1)) {
            wQuickFindBox.union(flatIndex, flattenGrid(row, col + 1) - 1);
            wQuickFindFull.union(flatIndex, flattenGrid(row, col + 1) - 1);
        }

        // Check and Open Up
        if (isOnGrid(row - 1, col) && isOpen(row - 1, col)) {
            wQuickFindBox.union(flatIndex, flattenGrid(row - 1, col) - 1);
            wQuickFindFull.union(flatIndex, flattenGrid(row - 1, col) - 1);
        }

        // Check and Open Down
        if (isOnGrid(row + 1, col) && isOpen(row + 1, col)) {
            wQuickFindBox.union(flatIndex, flattenGrid(row + 1, col) - 1);
            wQuickFindFull.union(flatIndex, flattenGrid(row + 1, col) - 1);
        }

        // debug
        // runTests();
    }

    private int flattenGrid(int row, int col) {
        return boxSize * (row - 1) + col;
    }

    public boolean percolates() {
        return wQuickFindBox.find(vTop) == wQuickFindBox.find(vBottom);
    }

    public boolean isFull(int row, int col) {
        validateSite(row, col);
        return wQuickFindFull.find(vTop)==wQuickFindFull.find(flattenGrid(row, col) - 1);
    }
}
