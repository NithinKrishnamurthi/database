package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Database{
    HashMap<String, Table> tables;

    public Database() {
        this.tables = new HashMap<String, Table>();
    }

    public void addTable(String s, Table t) {
        tables.put(s, t);
    }

    public String transact(String query) {

        return eval(query);
    }

    // Various common constructs, simplifies parsing.
    private final String REST = "\\s*(.*)\\s*",
            COMMA = "\\s*,\\s*",
            AND = "\\s+and\\s+";

    // Stage 1 syntax, contains the command name.
    private final Pattern CREATE_CMD = Pattern.compile("create table " + REST),
            LOAD_CMD = Pattern.compile("load " + REST),
            STORE_CMD = Pattern.compile("store " + REST),
            DROP_CMD = Pattern.compile("drop table " + REST),
            INSERT_CMD = Pattern.compile("insert into " + REST),
            PRINT_CMD = Pattern.compile("print " + REST),
            SELECT_CMD = Pattern.compile("select " + REST);

    // Stage 2 syntax, contains the clauses of commands.
    private final Pattern CREATE_NEW = Pattern.compile("(\\S+)\\s+\\((\\S+\\s+\\S+\\s*" +
            "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
            SELECT_CLS = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+" +
                    "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+" +
                    "([\\w\\s+\\-*/'<>=!]+?(?:\\s+and\\s+" +
                    "[\\w\\s+\\-*/'<>=!]+?)*))?"),
            CREATE_SEL = Pattern.compile("(\\S+)\\s+as select\\s+" +
                    SELECT_CLS.pattern()),
            INSERT_CLS = Pattern.compile("(\\S+)\\s+values\\s+(.+?" +
                    "\\s*(?:,\\s*.+?\\s*)*)");

    public void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected a single query argument");
            return;
        }

        eval(args[0]);
    }

    public String eval(String query) {
        Matcher m;
        if ((m = CREATE_CMD.matcher(query)).matches()) {
            createTable(m.group(1));
        } else if ((m = LOAD_CMD.matcher(query)).matches()) {
            try {
                return loadTable(m.group(1));
            }
            catch(FileNotFoundException e){
                return "ERROR: TBL file not found: " + m.group(1) + ".tbl";
            }
        } else if ((m = STORE_CMD.matcher(query)).matches()) {
            storeTable(m.group(1));
        } else if ((m = DROP_CMD.matcher(query)).matches()) {
            dropTable(m.group(1));
        } else if ((m = INSERT_CMD.matcher(query)).matches()) {
            insertRow(m.group(1));
        } else if ((m = PRINT_CMD.matcher(query)).matches()) {
            return printTable(m.group(1));
        } else if ((m = SELECT_CMD.matcher(query)).matches()) {
            return select(m.group(1));
        } else {
            System.err.printf("Malformed query: %s\n", query);
        }
        return "";
    }

    private String createTable(String expr) {
        Matcher m;
        if ((m = CREATE_NEW.matcher(expr)).matches()) {
            return createNewTable(m.group(1), m.group(2).split(COMMA));
        } else if ((m = CREATE_SEL.matcher(expr)).matches()) {
            return createSelectedTable(m.group(1), m.group(2), m.group(3), m.group(4));
        } else {
            System.err.printf("Malformed create: %s\n", expr);
            return "";
        }
    }

    private String createNewTable(String name, String[] cols) {
        Column[] columns = new Column[cols.length];
        for (int i = 0; i < cols.length; i++) {
            String colName = cols[i].split(" ")[0];
            if (isLowerCase(cols[i].split(" ")[1])){
                Data colData = Data.valueOf(cols[i].split(" ")[1].toUpperCase());
                columns[i] = new Column(colName,colData);
            }
            else{
                throw new RuntimeException("Invalid type");
            }
        }
        Table t = new Table(columns);
        this.addTable(name,t);
        return "";
    }

    private String createSelectedTable(String name, String exprs, String tables, String conds) {
        System.out.printf("You are trying to create a table named %s by selecting these expressions:" +
                " '%s' from the join of these tables: '%s', filtered by these conditions: '%s'\n", name, exprs, tables, conds);
        return "";
    }

    private String loadTable(String name) throws FileNotFoundException {
        //POSSIBLE ERRORS:
        //Scanner was implemented incorrectly
        //ARRAYLIST IN FOR LOOP COULD POSSIBLY ADD MORE VALUES EACH CREATION??
        //Initialize new Scanner and Regex Objects

        File file = new File(name + ".tbl");
        Scanner input = new Scanner(file);
        //Get the Column Titles
        String line = input.nextLine();
        String[] columnExpressions = line.split(",");
        Column[] columnArray = new Column[columnExpressions.length];
        for (int i = 0;i<columnExpressions.length;i++) {
            String[] colData = columnExpressions[i].split(" ");
            columnArray[i] = new Column(colData[0],Data.valueOf(colData[1].toUpperCase()));
        }

        Table table = new Table(columnArray);
        while (input.hasNextLine()) {
            //get the next line
            line = input.nextLine();
            //split the next line
            String[] values = line.split(",");
            table.addRow(values);
        }
        input.close();
        this.addTable(name,table);
        return "";
    }

    private void storeTable(String name) {
        System.out.printf("You are trying to store the table named %s\n", name);
    }

    private void dropTable(String name) {
        tables.remove(name);
    }

    private void insertRow(String expr) {
        Matcher m = INSERT_CLS.matcher(expr);
        if (!m.matches()) {
            System.err.printf("Malformed insert: %s\n", expr);
            return;
        }
        else{
            tables.get(m.group(1)).addRow(m.group(2).split(","));
        }
    }

    private String printTable(String name) {
        Table t = tables.get(name);
        return t.toString();
    }

    private String select(String expr) {
        Matcher m = SELECT_CLS.matcher(expr);
        if (!m.matches()) {
            System.err.printf("Malformed select: %s\n", expr);
        }

        return select(m.group(1), m.group(2), m.group(3));
    }

    private String select(String exprs, String tables, String conds) {
        String[] tableNames = tables.split(",\\s*");
        Table[] tablesArray = new Table[tableNames.length];
        for(int i = 0;i<tablesArray.length;i++){
            tablesArray[i] = this.tables.get(tableNames[i]);
        }
        Table t = Table.Join(tablesArray);
        return t.toString();

        //System.out.printf("You are trying to select these expressions:" +
        //        " '%s' from the join of these tables: '%s', filtered by these conditions: '%s'\n", exprs, tables, conds);
    }

    private boolean isLowerCase(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) && Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;

    }
}
