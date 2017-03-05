package db;

/**
 * Created by nithin on 3/4/17.
 */
public class Operand {
    Data type;
    String value;
    public Operand(Data type, String value){
        this.type = type;
        this.value = value;
    }
    public Operand(String value){
        this.type = Data.type(value);
        this.value = value;
    }
    public Operand(Item i){
        this.type = i.column.type;
        this.value = i.value;
    }
    public static Operand Operate(Operand i1, Operand i2, Operator operator){
        return operator.operate(i1,i2);
    }
}
