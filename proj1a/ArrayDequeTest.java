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


        test.printDeque();
    }
}
