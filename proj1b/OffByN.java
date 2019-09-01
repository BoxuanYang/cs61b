import java.lang.Math;

public class OffByN implements CharacterComparator {
    int N;
    OffByN(int N){
        this.N = N;
    }

    public boolean equalChars(char x, char y){
        return Math.abs(x - y) == N;
    }
}
