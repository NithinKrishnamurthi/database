/**
 * Created by nithin on 2/9/17.
 */
public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == -this.n || x - y == this.n) {
            return true;
        }
        return false;
    }
}
