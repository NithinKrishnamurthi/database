/**
 * Created by nithin on 2/9/17.
 */

import org.junit.Assert;
import org.junit.Test;

public class TestArrayDeque1B {
    @Test
    public void testArrayDequeMethods() {
        /* Copied partially from StudentArrayDequeLauncher */
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        double numberBetweenZeroAndOne;
        OperationSequence fs = new OperationSequence();
        for (int i = 0; i < 5000; i += 1) {
            numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                fs.addOperation(new DequeOperation("addLast", i));
            } else {
                sad.addFirst(i);
                ads.addFirst(i);
                fs.addOperation(new DequeOperation("addFirst", i));
            }
        }
        for (int i = 0; i < 5000; i += 1) {
            numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                fs.addOperation(new DequeOperation("removeFirst"));
                Assert.assertEquals(fs.toString(), sad.removeFirst(), ads.removeFirst());
            } else {
                sad.removeLast();
                ads.removeLast();
                fs.addOperation(new DequeOperation("removeLast"));
                Assert.assertEquals(fs.toString(), sad.removeFirst(), ads.removeFirst());
            }
        }
    }


}
