/**
 * Created by nithin on 2/9/17.
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == -1 || x - y == 1) {
            return true;
        }
        return false;
    }
}
