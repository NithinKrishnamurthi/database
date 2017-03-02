package db;

/**
 * Created by thomashli on 2/26/17.
 */
public class Item {
    Column column;
    String value;
    public Item(Column column,String value) {
        this.column = column;
        this.value = value;
    }
    public boolean isEqual(Item item){
        return (this.column.equals(item.column) && this.value == item.value);
    }
    public String toString(){
        return value;
    }
}
