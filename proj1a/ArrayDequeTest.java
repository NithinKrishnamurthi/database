/**
 * Created by nithin on 2/2/17.
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        ArrayDeque<String> test = new ArrayDeque<String>();
//        System.out.println(test.removeFirst());
//        System.out.println(test.size());
        test.addFirst("back");
        test.addFirst("back");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.addFirst("front");
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
//        System.out.println(test.size());
        test.removeFirst();

        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        for (int i = 0; i < 10000; i++) {
            double test1 = Math.random() * 100;
            if (test1 > 75) {
                arr.addFirst(i);
            } else if (test1 > 50) {
                arr.addLast(i);
            } else if (test1 > 25) {
                arr.removeFirst();
            } else {
                arr.removeLast();
            }
        }
        arr.printDeque();
    }
}
