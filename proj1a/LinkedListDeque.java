/**
 * Created by nithin on 1/31/17.
 */
public class LinkedListDeque<Item> implements Deque<Item>{
    private class ItemNode {
        private Item item;
        private ItemNode prev;
        private ItemNode next;
        private ItemNode() {
            this.item = null;
            this.prev = this;
            this.next = this;
        }
        private ItemNode(Item i, ItemNode p, ItemNode n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }
    private ItemNode sentinel;
    private int size;
    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }
    public LinkedListDeque() {
        sentinel = new ItemNode();
        size = 0;
    }
    @Override
    public Item get(int i) {
        ItemNode p = sentinel.next;
        while (i > 0) {
            i = i - 1;
            p = p.next;
        }
        if (i == 0) {
            return p.item;
        }
        return null;
    }

    public Item getRecursive(int i) {
        return helperRecursive(i, sentinel.next);
    }


    private Item  helperRecursive(int i, ItemNode p) {
        if (i == 0 || p == sentinel) {
            return p.item;
        } else {
            return helperRecursive(i - 1, p.next);
        }
    }
    @Override
    public void addFirst(Item item) {
        sentinel.next = new ItemNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;

    }
    @Override
    public void addLast(Item item) {
        sentinel.prev = new ItemNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    @Override
    public Item removeFirst() {
        Item value = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return value;
    }
    @Override
    public Item removeLast() {
        Item value = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return value;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ItemNode i = sentinel.next;
        while (i != sentinel) {
            System.out.print(i.item + " ");
            i = i.next;
        }
    }
}
