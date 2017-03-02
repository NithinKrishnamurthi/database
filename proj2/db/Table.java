package db;

import java.util.*;

/**
 * Created by nithin on 2/17/17.
 */
public class Table {

    Column[] columns;
    ArrayList<Row> rows;
    public Table(Column[] columns){
        this.columns = columns;
        this.rows = new ArrayList<>();
    }
    public static void main(String[] args){
        Column[] columns = {new Column("x",Data.STRING),new Column("y",Data.STRING)};
        Column[] columns2 = {new Column("x",Data.STRING),new Column("z",Data.STRING)};
        Table t = new Table(columns);
        Table t2 = new Table(columns2);
        Item x1 = new Item(new Column("x",Data.FLOAT),"2");
        Item x2 = new Item(new Column("y",Data.STRING),"2.0");
        Item x3 = new Item(new Column("z",Data.STRING),"2.5");
        Item x4 = new Item(new Column("x",Data.INT),"2");
        Item x5 = new Item(new Column("x",Data.INT),"2");
        String[] items1 = {"2","5"};
        String[] items2 = {"8","3"};
        String[] items3 = {"13","7"};
        String[] items4 = {"2","4"};
        String[] items5 = {"8","9"};
        String[] items6 = {"10","1"};

        t.addRow(items1);
        t.addRow(items2);
        t.addRow(items3);
        t2.addRow(items4);
        t2.addRow(items5);
        t2.addRow(items6);
        Table[] tableArray = {t,t2};
        System.out.println(t);
        System.out.println(t2);

        System.out.println(Join(tableArray));

    }

    // Simple Select functionality
    public Table subTable(String[] colNames){
        Column[] subTableCols = new Column[colNames.length];
        for(int i = 0; i<colNames.length;i++){
            for(Column column: columns){
                if(column.name.equals(colNames[i])){
                    subTableCols[i] = column;
                }
            }

        }
        Table subTable = new Table(subTableCols);
        for(Row row: rows){
            ArrayList<Item> rowItems = new ArrayList<>();
            for(Column c: subTable.columns) {
                for (Item i : row.items) {
                    if (c.equals(i.column)){
                        rowItems.add(i);
                    }
                }
                Item[] rowItemsArray = new Item[rowItems.size()];
                rowItemsArray = rowItems.toArray(rowItemsArray);
                subTable.addRow(new Row(rowItemsArray));
            }
        }
        return subTable;
    }
    // Select functionality done.

    public void addRow(String[] row){
        if(validateRow(row)){
            Item[] items = new Item[row.length];
            for(int i = 0;i<row.length;i++){
                items[i] = new Item(columns[i],row[i]);
            }
            addRow(new Row(items));
        }
    }

    public boolean validateRow(String[] row){
        return true;

    }
    public void addRow(Row row){
        for(int i = 0;i<columns.length;i++){
            if(!columns[i].equals(row.items[i].column)){
                throw new RuntimeException("Row Table size mismatch");
            }
        }
        rows.add(row);
    }

    public static <T> int indexOf(T[] array, T val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(val)) {
                return i;
            }
        }
        return -1;

    }

    public static Table helperJoin(Table table1, Table table2) {
        Table returnVal = new Table(table1.columnJoin(table2));
        for(Row i: table1.rows){
            for(Row j: table2.rows){
                Row join = i.rowJoin(j);
                if(join != null){
                    returnVal.addRow(join);
                }
            }
        }
        return returnVal;

    }
    public static String columnsToString(Column[] columns){
        String returnVal = "";
        boolean firstElem = true;
        for(Column c : columns){
            if(!firstElem){
                returnVal += ",";
            }
            returnVal += c.name + " " + c.type.toString().toLowerCase();
            firstElem = false;
        }
        return returnVal;

    }

    public String toString(){
        String returnVal = columnsToString(columns);
        for(Row r: this.rows){
            returnVal += "\n";
            returnVal += r;
        }
        return returnVal;
    }

    //Forgive me Hug for the ugliness below. I didn't know how to do this without repeating myself. I failed you.

    public Column[] columnJoin(Table table){
        Column[] matchingCols = matchingCols(table);
        if(matchingCols.length == 0){
            return cartesianColumnJoin(table);
        }
        else{
            ArrayList<Column> cols = new ArrayList<>();
            for(Column i: matchingCols){
                cols.add(i);
            }
            for(Column i: this.columns){
                if(indexOf(matchingCols,i) == -1){
                    cols.add(i);
                }
            }
            for(Column i: table.columns){
                if(indexOf(matchingCols,i) == -1){
                    cols.add(i);
                }
            }
            Column[] colsArray = new Column[cols.size()];
            colsArray = cols.toArray(colsArray);
            return colsArray;
        }
    }
    public Column[] cartesianColumnJoin(Table table){
        ArrayList<Column> cols = new ArrayList<>();
        for(Column i: this.columns){
            cols.add(i);
        }
        for(Column i: table.columns){
            cols.add(i);
        }
        Column[] colsArray = new Column[cols.size()];
        colsArray = cols.toArray(colsArray);
        return colsArray;
    }
    public Column[] matchingCols(Table table) {
        ArrayList<Column> matchCols = new ArrayList<>();
        for (Column i : this.columns) {
            for (Column j : table.columns) {
                if (i.equals(j)) {
                    matchCols.add(i);
                }
            }
        }
        Column[] matchColsArray = new Column[matchCols.size()];
        matchColsArray = matchCols.toArray(matchColsArray);
        return matchColsArray;
    }
    public static Table Join(Table... tables){
        if(tables.length == 1){
            return tables[0];
        }
        else{
            Table finalVal = tables[0];
            for(int i = 1;i<tables.length;i++){
                finalVal = helperJoin(finalVal,tables[i]);
            }
            return finalVal;
        }

    }

}
