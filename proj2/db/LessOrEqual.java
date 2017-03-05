package db;

/**
 * Created by nithin on 3/4/17.
 */
public class LessOrEqual implements Conditional {
    @Override
    public boolean operate(Operand i1, Operand i2) {
        Conditional comparator = new Greater();
        return !comparator.operate(i1,i2);
    }
}
