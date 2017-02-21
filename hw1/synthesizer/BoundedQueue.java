package synthesizer;

import java.util.Iterator;

/**
 * Created by nithin on 2/20/17.
 */
public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();          // return size of the buffer

    int fillCount();         // return number of items currently in the buffer

    void enqueue(T x);  // add item x to the end

    T dequeue();        // delete and return item from the front

    T peek();

    Iterator<T> iterator();


    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }     // is the buffer empty (fillCount equals zero)?

    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }

}
