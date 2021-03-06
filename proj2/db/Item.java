package db;


/**
 * Created by thomashli on 2/26/17.
 */
public class Item {
    Column column;
    String value;

    public Item(Operand o, String colName){
        this.column = new Column(colName,o.type);
        this.value = o.value;
    }

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
}
