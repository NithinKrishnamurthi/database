package db;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nithin on 2/17/17.
 */
public class Table {
    private ArrayList<ArrayList<String>> values;
    private ArrayList<String> colNames;
    private int numRows;
    private int numCols;

    public static void main(String[] args){
        String[] colNames = {"x","y"};
        Table table = new Table(colNames);
        String[] firstRow = {"1","2"};
        table.addRow(firstRow);
        table.addRow(firstRow);
        table.addCol(firstRow,"z");
        table.addCol(firstRow,"a");
        table.printValues();
        System.out.print(Arrays.toString(table.getCol("z")));

    }

    public Table(String[] colNames){
        values = new ArrayList<>();
        addRow(colNames);
        this.colNames = toArrayList(colNames);
        numRows = 1;
        numCols = colNames.length;
    }

    public String[] getColNames() {
        return toArray(colNames);
    }

    public Table join(Table t){
        for(int i = 0; i < t.getNumRows(); i++){

        }
        return null;

    }

    public int getNumRows(){
        return numRows;
    }
    public int getNumCols() {
        return numCols;
    }


    public void addRow(String[] row) {
        values.add(toArrayList(row));
        numRows += 1;
    }
    public void addCol(String[] col,String colName){
        colNames.add(colName);
        values.get(0).add(colName);
        for(int i = 0; i<col.length;i++){
            values.get(i+1).add(col[i]);
        }
        numCols += 1;
    }

    public String[] getCol(int col){
        String [] result = new String[numRows];
        for(int i = 0; i < numRows;i++){
            result[i] = values.get(i).get(col);
        }
        return result;

    }

    public String[] getCol(String colName){
        int col = indexOfCol(colName);
        return getCol(col);

    }
    public int indexOfCol(String colname){
        for(int i = 0;i<colNames.size();i++){
            if(colname == colNames.get(i)){
                return i;
            }
        }
        return -1;
    }



    public void printValues(){
        for(ArrayList<String> i: values){
            for(String j: i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }



    // Helper functions
    public static ArrayList<String> toArrayList(String[] input){
        ArrayList<String> returnVal = new ArrayList<>();
        for(String i:input){
            returnVal.add(i);
        }
        return returnVal;
    }

    public static String[] toArray(ArrayList<String> input){
        String[] returnVal = new String[input.size()];
        for(int i = 0;i<returnVal.length;i++){
            returnVal[i] = input.get(i);
        }
        return returnVal;

    }
}
