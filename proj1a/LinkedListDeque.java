/**
 * Created by nithin on 1/31/17.
 */
public class LinkedListDeque <Item> {
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
    private ItemNode p;
    private int size;
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
    public Item get(int i){
        p = p.next;
        while(i > 0){
            i = i - 1;
            p = p.next;
        }
        if(i == 0){
            p = sentinel;
            return p.item;
        }
        p = sentinel;
        return null;
    }

    public Item getRecursive(int i){
        p = p.next;
        if(i == 0) {
            p = sentinel;
            return p.item;
        }
        else{
            return getRecursive(i-1);
        }

    }
    public void addFirst(Item item) {
        sentinel.next = new ItemNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;

    }
    public void addLast(Item item) {
        sentinel.prev = new ItemNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    public Item removeFirst() {
        Item value = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return value;
    }
    public Item removeLast() {
        Item value = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return value;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        ItemNode i = sentinel.next;
        while (i != sentinel) {
            System.out.print(i.item + " ");
            i = i.next;
        }
    }
}
