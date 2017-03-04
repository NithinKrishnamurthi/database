package db;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nithin on 2/17/17.
 */
public class Table {

    Column[] columns;
    ArrayList<Row> rows;

    public Table(Column[] columns) {
        this.columns = columns;
        this.rows = new ArrayList<>();
    }

    public Table(Column column) {
        Column columns[] = {column};
        this.columns = columns;
        this.rows = new ArrayList<>();
    }

    public void addRow(String s) {
        String[] value = {s};
        if (validateRow(value)) {
            this.addRow(value);
        }
    }

    public Column getCol(String s) {
        for (Column c : columns) {
            if (c.name.equals(s)) {
                return c;
            }
        }
        throw new ColumnNotFoundException("Column " + s + " not found");
    }

    public static void main(String[] args) {
        Column[] columns = {new Column("x", Data.STRING), new Column("y", Data.STRING)};
        Column[] columns2 = {new Column("y", Data.INT), new Column("z", Data.STRING)};
        Table t = new Table(columns);
        Table t2 = new Table(columns2);
        String[] items1 = {"2", "3"};
        String[] items2 = {"8", "5"};
        String[] items3 = {"13", "7"};
        String[] items4 = {"5", "8"};
        t.addRow(items1);
        t.addRow(items2);
        t2.addRow(items3);
        t2.addRow(items4);
        System.out.print(Arrays.toString("x + '2'".split("\\s+")));
        System.out.print(t.evalColExp("x + '2' as z"));


    }

    public Table evalColExp(String s) {
        s = s.trim();
        String[] operands = s.split("\\s+as\\s+");
        if (operands.length == 1) {
            return getColTable(operands[0].trim());
        } else {
            String colName = operands[1].trim();
            Matcher matcher;
            Pattern pattern = Pattern.compile("(\\w+)\\s*(\\W+)\\s*(\\w+)");
            matcher = pattern.matcher(operands[0].trim());
            matcher.find();
            Table t1 = getColTable(matcher.group(1).trim());
            String operator = matcher.group(2).trim();
            Data type = Data.type(matcher.group(3).trim());
            if (type == null) {
                Table t2 = getColTable(matcher.group(3));
                return Table.operate(colName, operator, t1, t2);
            } else {
                return Table.operate(colName, operator, t1, matcher.group(3), type);
            }
        }
    }

    // Simple Select functionality
    public Table getColTable(String colName) {
        Table colTable = new Table(getCol(colName));
        for (Row row : rows) {
            for (Item i : row.items) {
                if (colName.equals(i.column.name)) {
                    colTable.addRow(i.value);
                }
            }

        }

        return colTable;
    }
    // Select functionality done.

    public void addRow(String[] row) {
        if (validateRow(row)) {
            Item[] items = new Item[row.length];
            for (int i = 0; i < row.length; i++) {
                items[i] = new Item(columns[i], row[i]);
            }
            addRow(new Row(items));
        }
    }

    public boolean validateRow(String[] row) {
        try {
            for (int i = 0; i < row.length; i++) {
                if (!columns[i].type.equals(Data.type(row[i]))) {
                    throw new RowAdditionException("Cannot add object of type " + Data.type(row[i]) + " to column of type " + columns[i].type);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RowAdditionException("Size of Row and Table do not match");
        }
        return true;

    }

    public void addRow(Row row) {
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i].equals(row.items[i].column)) {
                throw new RuntimeException("Row Table mismatch");
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

    public static Table operate(String colName, String prompt, Table table1, Table table2) {
        Table t = new Table(new Column(colName, Data.operate(table1.columns[0].type, table2.columns[0].type)));
        for (int i = 0; i < table1.rows.size(); i++) {
            t.addRow(Item.operate(colName, prompt, table1.rows.get(i).items[0], table2.rows.get(i).items[0]).value);
        }
        return t;
    }

    public static Table operate(String colName, String prompt, Table table1, String value, Data type) {
        Table t = new Table(new Column(colName, Data.operate(table1.columns[0].type, type)));
        for (int i = 0; i < table1.rows.size(); i++) {
            Item item = table1.rows.get(i).items[0];
            t.addRow(Item.operate(colName, prompt, item, new Item(t.columns[0], value)).value);
        }
        return t;
    }


    public static String columnsToString(Column[] columns) {
        String returnVal = "";
        boolean firstElem = true;
        for (Column c : columns) {
            if (!firstElem) {
                returnVal += ",";
            }
            returnVal += c.name + " " + c.type.toString().toLowerCase();
            firstElem = false;
        }
        return returnVal;

    }

    public String toString() {
        String returnVal = columnsToString(columns);
        for (Row r : this.rows) {
            returnVal += "\n";
            returnVal += r;
        }
        return returnVal;
    }

    //Forgive me Hug for the ugliness below. I didn't know how to do this without repeating myself. I failed you.

    public Column[] columnJoin(Table table) {
        Column[] matchingCols = matchingCols(table);
        if (matchingCols.length == 0) {
            return cartesianColumnJoin(table);
        } else {
            ArrayList<Column> cols = new ArrayList<>();
            for (Column i : matchingCols) {
                cols.add(i);
            }
            for (Column i : this.columns) {
                if (indexOf(matchingCols, i) == -1) {
                    cols.add(i);
                }
            }
            for (Column i : table.columns) {
                if (indexOf(matchingCols, i) == -1) {
                    cols.add(i);
                }
            }
            Column[] colsArray = new Column[cols.size()];
            colsArray = cols.toArray(colsArray);
            return colsArray;
        }
    }

    public Column[] cartesianColumnJoin(Table table) {
        ArrayList<Column> cols = new ArrayList<>();
        for (Column i : this.columns) {
            cols.add(i);
        }
        for (Column i : table.columns) {
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

    public static Table Join(Table... tables) {
        if (tables.length == 1) {
            return tables[0];
        } else {
            Table finalVal = tables[0];
            for (int i = 1; i < tables.length; i++) {
                finalVal = helperJoin(finalVal, tables[i]);
            }
            return finalVal;
        }

    }

    public static Table helperJoin(Table table1, Table table2) {
        Table returnVal = new Table(table1.columnJoin(table2));
        for (Row i : table1.rows) {
            for (Row j : table2.rows) {
                Row join = i.rowJoin(j);
                if (join != null) {
                    returnVal.addRow(join);
                }
            }
        }
        return returnVal;

    }

    public static Table addColTables(Table... tables) {
        Column[] colArray = new Column[tables.length];
        for (int i = 0; i < tables.length; i++) {
            colArray[i] = tables[i].columns[0];
        }
        Table returnVal = new Table(colArray);
        for (int i = 0; i < tables[0].rows.size(); i++) {
            String[] rowVals = new String[tables.length];
            for (int j = 0; j < tables.length; j++) {
                rowVals[j] = tables[j].rows.get(i).items[0].value;
            }
            returnVal.addRow(rowVals);

        }
        return returnVal;

    }

}
