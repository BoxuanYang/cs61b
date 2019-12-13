package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    WeightedQuickUnionUF wqu;
    enum Site {
        OPEN,
        CLOSED
    }

    private int N;

    private Site[][] sites;
    private int open;

    public Percolation(int N){
        if (N <= 0){
            throw new IllegalArgumentException();
        }
        this.N = N;

        //2 virtual nodes at the top and bottom, respectively
        wqu = new WeightedQuickUnionUF((N * N) + 2);
        //connect (N*N) to the top row
        for(int i = 0; i < N; i++){
            int index= index(0, i);
            wqu.union(index, N * N);
        }
        //connect (N*N) + 1 to the bottom row
        for(int i = 0; i < N; i++){
            int index= index(N - 1, i);
            wqu.union(index, (N * N) + 1);
        }

        sites = new Site[N][N];
        //initialize everything to be close
        for(int x = 0; x < N; x++){
            for (int y = 0; y < N; y++){
                sites[x][y] = Site.CLOSED;
            }
        }

        open = 0;

    }

    public void open(int row, int col){
        sites[row][col] = Site.OPEN;
        int index = index(row, col);

        //if there are any open sites in the neighbor, connnect them

        //check horizontally
        for(int i = -1; i <= 1; i++){
            if(i == 0 || (col + i) < 0 || (col + i) >= N)
                continue;

            if(isOpen(row, col + i)){
                int neighborIndex = index(row, col + i);
                wqu.union(index, neighborIndex);
            }
        }

        //check vertically
        for(int i = -1; i <= 1; i++){
            if(i == 0 || (row + i) < 0 || (row + i) >= N)
                continue;

            if(isOpen(row + i, col)){
                int neighborIndex = index(row + i, col);
                wqu.union(index, neighborIndex);
            }
        }

        open++;
    }

    public int index(int row, int col){
        return col + N * row;
    }
    public boolean isOpen(int row, int col){
        return sites[row][col] == Site.OPEN;
    }

    public boolean isFull(int row, int col) {
        //non-open square cannot be full
        if (!isOpen(row, col))
            return false;

        int index = index(row, col);

        return wqu.connected(index, N * N);
    }

    public int numberOfOpenSites(){
        return open;
    }

    public boolean percolates(){
        return wqu.connected(N*N, N*N + 1);
    }

    public static void main(String[] args) {
        Percolation per = new Percolation(5);
        per.open(0,0);
        per.open(1,0);
        per.open(2,0);
        System.out.println(!per.percolates());
        per.open(3,0);
        System.out.println(!per.percolates());
        per.open(4,0);
        System.out.println(per.percolates());
    }
}
