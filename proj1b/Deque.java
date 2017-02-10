/**
 * Created by nithin on 2/9/17.
 */
public interface Deque<Item> {
    public boolean isEmpty();

    public Item get(int i);

    public void addFirst(Item item);

    public void addLast(Item item);

    public Item removeFirst();

    public Item removeLast();

    public int size();

    public void printDeque();


}
