package hw2;
import java.util.concurrent.ThreadLocalRandom;


public class PercolationStats {
    private int N;
    private int T;
    private PercolationFactory pf;
    private int[] x;

    public PercolationStats (int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.T = T;
        this.pf = new PercolationFactory(N);
        this.x = new int[T];

        //do the experiment for T times, store the number in array t
        for (int t = 0; t < T; t++) {
            Percolation p = pf.make(N);
            int times = 0;

            while (!p.percolates()) {
                // nextInt is [min, max)
                int random = ThreadLocalRandom.current().nextInt(0, N * N);
                //index = col + N * row;
                int col = random % N;
                int row = (random - col) / N;
                p.open(row, col);
                times++;
            }

            x[t] = times;

        }
    }

    public double mean(){
        int sum = 0;
        for(int i = 0; i < T; i++){
            sum += x[i];
        }

        return (double)(sum)/N;
    }

    public double stddev(){
        double sum = 0;
        double mean = mean();
        for(int t = 0; t < T; t++){
            sum += Math.pow((x[t] - mean), 2.0);
        }
        return Math.sqrt(sum/(T-1));
    }

    public double confidenceLow(){
        double deviation = stddev();
        double mean = mean();
        double sqrtT = Math.sqrt(T);
        return mean - 1.96 * deviation / sqrtT;
    }

    public double confidenceHigh(){
        double deviation = stddev();
        double mean = mean();
        double sqrtT = Math.sqrt(T);
        return mean + 1.96 * deviation / sqrtT;
    }

}

