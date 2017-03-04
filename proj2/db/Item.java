package db;


/**
 * Created by thomashli on 2/26/17.
 */
public class Item {
    Column column;
    String value;

    public Item(Column column, String value) {
        this.column = column;
        this.value = value;
    }

    public boolean isEqual(Item item) {
        return (this.column.equals(item.column) && this.value.equals(item.value));
    }

    public String toString() {
        return value;
    }

    public static Item operate(String name, String prompt, Item i1, Item i2) {
        Data type = Data.operate(i1.column.type, i2.column.type);
        switch (prompt) {
            case "+":
                switch (type) {
                    case INT:
                        return new Item(new Column(name, type),
                                String.valueOf(Integer.parseInt(i1.value)
                                        + Integer.parseInt(i2.value)));
                    case FLOAT:
                        return new Item(new Column(name, type),
                                String.valueOf(Float.parseFloat(i1.value)
                                        + Float.parseFloat(i2.value)));
                    case STRING:
                        return new Item(new Column(name, type),
                                i1.value.substring(0, i1.value.length() - 1)
                                        + i2.value.substring(1));
                }
            case "-":
                switch (type) {
                    case INT:
                        return new Item(new Column(name, type),
                                String.valueOf(Integer.parseInt(i1.value) - Integer.parseInt(i2.value)));
                    case FLOAT:
                        return new Item(new Column(name, type),
                                String.valueOf(Float.parseFloat(i1.value) - Float.parseFloat(i2.value)));
                }
            case "/":
                switch (type) {
                    case INT:
                        return new Item(new Column(name, type),
                                String.valueOf(Integer.parseInt(i1.value) / Integer.parseInt(i2.value)));
                    case FLOAT:
                        return new Item(new Column(name, type),
                                String.valueOf(Float.parseFloat(i1.value) / Float.parseFloat(i2.value)));
                }
            case "*":
                switch (type) {
                    case INT:
                        return new Item(new Column(name, type),
                                String.valueOf(Integer.parseInt(i1.value) * Integer.parseInt(i2.value)));
                    case FLOAT:
                        return new Item(new Column(name, type),
                                String.valueOf(Float.parseFloat(i1.value) * Float.parseFloat(i2.value)));
                }
        }
        throw new IllegalOperationException("Cannot perform operation between " + i1.column.type + " and " + i2.column.type);
    }
}
