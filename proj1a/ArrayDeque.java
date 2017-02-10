/**
 * Created by nithin on 2/2/17.
 */
public class ArrayDeque<Item> implements Deque<Item>{
    private Item[] items;
    private int size;
    private double usageFactor = 0.25;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }
    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        size = size + 1;
        updateNextFirstAdd();
    }
    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        size = size + 1;
        updateNextLastAdd();
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = nextFirst;
        for (int j = 0; j < size; j++) {
            i += 1;
            i = i % items.length;
            System.out.print(items[i]);
            System.out.print(' ');
        }
    }
    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size * 1.0 / items.length < usageFactor && items.length >= 16) {
            resize(items.length / 2);
        }
        size = size - 1;
        updateNextFirstRemove();
        Item value = items[nextFirst];
        items[nextFirst] = null;
        return value;
    }
    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size * 1.0 / items.length < usageFactor && items.length > 8) {
            resize(items.length / 2);
        }
        size = size - 1;
        updateNextLastRemove();
        Item value = items[nextLast];
        items[nextLast] = null;
        return value;
    }
    @Override
    public Item get(int index) {
        if (index > items.length || index < 0) {
            System.out.println("Not a valid index");

        }
        index = (index + nextFirst + 1) % items.length;
        return items[index];
    }

    //Helper functions

    private Item[] toArray() {
        Item[] newItems = (Item[]) new Object[items.length];
        int i = nextFirst;

        for (int j = 0; j < items.length; j++) {
            i = i + 1;
            i = i % items.length;
            newItems[j] = items[i];
        }
        return newItems;
    }

    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        System.arraycopy(toArray(), 0, newItems, 0, size);
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }
    private void updateNextFirstAdd() {
        nextFirst = nextFirst - 1;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
    }

    private void updateNextFirstRemove() {
        nextFirst = nextFirst + 1;
        nextFirst = nextFirst % items.length;
    }

    private void updateNextLastAdd() {
        nextLast = nextLast + 1;
        nextLast = nextLast % items.length;
    }
    private void updateNextLastRemove() {
        nextLast = nextLast - 1;
        if (nextLast == -1) {
            nextLast = items.length - 1;
        }

    }
}
