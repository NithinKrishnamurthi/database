package db;

/**
 * Created by nithin on 3/3/17.
 */
public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(String message){
        super(message);
    }
}
