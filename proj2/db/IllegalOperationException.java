package db;

/**
 * Created by nithin on 3/2/17.
 */
public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String message){
        super(message);
    }
}
