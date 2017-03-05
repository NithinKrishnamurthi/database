package db;

import java.util.Comparator;

/**
 * Created by nithin on 3/4/17.
 */
public class GreaterOrEqual implements Conditional{


    @Override
    public boolean operate(Operand i1, Operand i2) {
        Conditional comparator = new Less();
        return !comparator.operate(i1,i2);
    }
}
