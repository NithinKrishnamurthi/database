/**
 * Created by nithin on 2/9/17.
 */

import org.junit.Assert;
import org.junit.Test;

public class TestArrayDeque1B {
    public static void main(String[] args) {
        testArrayDequeMethods();
    }

    @Test
    public static void testArrayDequeMethods() {
        /* Copied partially from StudentArrayDequeLauncher */
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        OperationSequence fs = new OperationSequence();
        for (int i = 0; i < 10000; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                sad.addLast(i);
                ads.addLast(i);
                fs.addOperation(new DequeOperation("addLast", i));
                Assert.assertEquals(fs.toString(), sad, ads);
            } else if (numberBetweenZeroAndOne > 0.25 && numberBetweenZeroAndOne < 0.5) {
                sad.addFirst(i);
                ads.addFirst(i);
                fs.addOperation(new DequeOperation("addFirst", i));
                Assert.assertEquals(fs.toString(), sad, ads);
            } else if (numberBetweenZeroAndOne > 0.5 && numberBetweenZeroAndOne < 0.75) {
                sad.removeFirst();
                ads.removeFirst();
                fs.addOperation(new DequeOperation("removeFirst"));
                Assert.assertEquals(fs.toString(), sad, ads);
            } else {
                sad.removeLast();
                ads.removeLast();
                fs.addOperation(new DequeOperation("removeLast"));
                Assert.assertEquals(fs.toString(), sad, ads);
            }

        }
    }


}
