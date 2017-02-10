/**
 * Created by nithin on 2/9/17.
 */
public interface Deque<Item> {
    boolean isEmpty();

    Item get(int i);

    void addFirst(Item item);

    void addLast(Item item);

    Item removeFirst();

    Item removeLast();

    int size();

    void printDeque();


}
