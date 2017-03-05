package db;

/**
 * Created by nithin on 3/4/17.
 */
public class Greater implements Conditional {
    public boolean operate(Operand i1, Operand i2) {
        switch (i1.type){
            case FLOAT:
                switch (i2.type){
                    case FLOAT:
                        return Float.parseFloat(i1.value) > Float.parseFloat(i2.value);
                }
            case INT:
                switch (i2.type){
                    case INT:
                        return Integer.parseInt(i1.value) > Integer.parseInt(i2.value);
                }
            case STRING:
                switch (i2.type){
                    case STRING:
                        return i1.value.compareTo(i2.value) > 0;
                }
        }
        throw new IllegalOperationException("Cannot perform operation between " + i1.type + " and " + i2.type);
    }
}
