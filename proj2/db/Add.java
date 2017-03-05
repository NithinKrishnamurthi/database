package db;

/**
 * Created by nithin on 3/4/17.
 */
public class Add implements Operator{


    @Override
    public Operand operate(Operand i1, Operand i2) {
        switch(i1.type){
            case FLOAT:
                switch (i2.type){
                    case FLOAT:
                        return new Operand(Data.FLOAT,String.valueOf(Float.parseFloat(i1.value) + Float.parseFloat(i2.value)));

                    case INT:
                        return new Operand(Data.FLOAT,String.valueOf(Float.parseFloat(i1.value) + Integer.parseInt(i2.value)));

                }
            case INT:
                switch (i2.type){
                    case FLOAT:
                        return new Operand(Data.FLOAT,String.valueOf(Integer.parseInt(i1.value) + Float.parseFloat(i2.value)));
                    case INT:
                        return new Operand(Data.FLOAT,String.valueOf(Integer.parseInt(i1.value) + Integer.parseInt(i2.value)));

                }
            case STRING:
                switch (i2.type){
                    case STRING:
                        return new Operand(Data.STRING,i1.value.substring(0, i1.value.length() - 1)
                                + i2.value.substring(1));
                }
            default:
                throw new IllegalOperationException("Cannot perform operation between " + i1.type + " and " + i2.type);
        }
    }
}
