package hw2;

public class PercolationFactory {
    int N;

    public Percolation make(int N) {
        return new Percolation(N);
    }

    public PercolationFactory(int N){
        this.N = N;
    }
}
