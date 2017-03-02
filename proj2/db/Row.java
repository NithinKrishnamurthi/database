package db;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import static db.Table.indexOf;


/**
 * Created by nithin on 2/24/17.
 */
public class Row {

    Item[] items;

    public Row(Item[] items) {
        this.items = items;
    }


    public Item getItem(String s) {
        try {
            for (Item item: this.items) {
                if (item.column.name.equals(s)) {
                    return item;
                }
            }
            throw new RuntimeException("Row does not have specified column");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Row rowJoin(Row row) {

        // Returns a string array of all similar column names
        String[] matchingCols = matchingCols(row);

        // Checks if there are no similar columns i.e. the number of matching columns is zero.
        if (matchingCols.length == 0) {

            //Returns the cartesian product.
            return cartesianProduct(row);
        } else {

            //ArrayList consisting of the final items of the row.
            ArrayList<Item> items = new ArrayList<>();

            for (String s : matchingCols) {
                //
                Item item = getItem(s);
                if (!item.isEqual(row.getItem(s))) {
                    return null;
                } else {
                    items.add(item);
                }
            }
            for (Item item: this.items){
                if(indexOf(matchingCols,item.column.name) == -1){
                    items.add(item);
                }
            }
            for (Item item: row.items) {
                if (indexOf(matchingCols,item.column.name)== -1){
                    items.add(item);
                }
            }
            Item[] itemsArray = new Item[items.size()];

            return new Row(items.toArray(itemsArray));
        }
    }


    public Row cartesianProduct(Row row) {
        ArrayList<Item> items = new ArrayList<>();
        for (Item i: this.items) {
            items.add(i);
        }
        for(Item i: row.items) {
            items.add(i);
        }
        Item[] itemsArray = new Item[items.size()];
        return new Row(items.toArray(itemsArray));
    }

    public String[] matchingCols(Row row) {
        ArrayList<String> matchCols = new ArrayList<>();
        for (Item i: this.items) {
            for (Item j: row.items) {
                if (i.column.name.equals(j.column.name)) {
                    matchCols.add(i.column.name);
                }
            }
        }
        String[] matchColsArray = new String[matchCols.size()];
        matchColsArray = matchCols.toArray(matchColsArray);
        return matchColsArray;

    }

    public String toString() {
        String returnVal = "";
        boolean firstElem = true;
        for(Item item:items){
            if(!firstElem){
                returnVal += ", ";
            }
            returnVal += item.value;
            firstElem = false;
        }
        return returnVal;
    }

    public static void main(String[] args) {
        Item x1 = new Item(new Column("x",Data.INT),"2");
        Item x2 = new Item(new Column("y",Data.FLOAT),"2.0");
        Item x3 = new Item(new Column("z",Data.FLOAT),"2.5");
        Item x4 = new Item(new Column("x",Data.INT),"2");
        Item x5 = new Item(new Column("x",Data.INT),"2");
        Item[] items1 = {x1,x2};
        Item[] items2 = {x3};
        Item[] items3 = {x5,x2,x3};
        Row row = new Row(items1);
        Row row2 = new Row(items2);
        System.out.println(row);
        System.out.println(row2);
        System.out.println(row.rowJoin(row2));
        Row row3 = new Row(items3);
        System.out.println(row2.rowJoin(row3));
    }
}

