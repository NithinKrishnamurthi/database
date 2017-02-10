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
                Assert.assertEquals(fs.toString(), sad, ads);
            } else {
                sad.addFirst(i);
                ads.addFirst(i);
                fs.addOperation(new DequeOperation("addFirst", i));
                Assert.assertEquals(fs.toString(), sad, ads);
            }
        }
        for (int i = 0; i < 5000; i += 1) {
            numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
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
