package db;

/**
 * Created by nithin on 3/3/17.
 */
public class ColumnNotFoundException extends RuntimeException {
    public ColumnNotFoundException(String message){
        super(message);
    }
}
