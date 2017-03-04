package db;

/**
 * Created by nithin on 3/3/17.
 */
public class MalformedCommandException extends RuntimeException {
    public MalformedCommandException(String message) {
        super(message);
    }
}
