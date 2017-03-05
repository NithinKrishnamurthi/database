package db;

/**
 * Created by nithin on 3/4/17.
 */
public class NotEquals implements Conditional {
    @Override
    public boolean operate(Operand i1, Operand i2) {
        return !i1.value.equals(i2.value);
    }
}
