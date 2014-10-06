
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class JRandomizedQueueTest {

    RandomizedQueue<Integer> rqInstance;

    //execute for each test, before executing test
    @Before
    public void before() {
        rqInstance = new RandomizedQueue<Integer>();
        rqInstance.enqueue(10);
        rqInstance.enqueue(20);
        rqInstance.enqueue(30);
        rqInstance.enqueue(40);
    }

    //execute for each test, after executing test
    @After
    public void after() {
        rqInstance = null;
    }

    @Test
    public void testDequeueSimple() {
        int k = rqInstance.size();
        for (int i = 0; i < k; i++) {
            rqInstance.dequeue();
        }
        assertEquals(0, rqInstance.size());
    }

    @Test
    public void testDequeuePulse() {
        int k = rqInstance.size();
        for (int i = 0; i < k; i++) {
            Integer sample = rqInstance.dequeue();
            rqInstance.enqueue(sample);
        }
        assertEquals(4, rqInstance.size());
    }

    @Test
    public void testDequeueComplex() {
        int k = rqInstance.size();
        for (int i = 0; i < k; i++) {
            rqInstance.dequeue();
        }
        assertEquals(0, rqInstance.size());
    }

    @Test
    public void testIsEmpty() {
        int k = rqInstance.size();
        for (int i = 0; i < k; i++) {
            rqInstance.dequeue();
        }
        assertEquals(true, rqInstance.isEmpty());
    }

    @Test
    public void testSample() {
        for (int i = 0; i < 3; i++) {
            System.out.println("new cycle");
            int k = rqInstance.size();
            for (int j = 0; j < k; j++) {
                System.out.println(rqInstance.sample());
            }
            assertEquals(4, rqInstance.size());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testItaretorNext() {
        Iterator<Integer> iter = rqInstance.iterator();
        while (iter.hasNext()) {
            System.out.println("inside iterator");
            iter.next();
        }
        iter.next();
    }

    @Test
    public void complexIterationDummy() {
        Integer[] outerArray = new Integer[]{10, 20, 30, 40};
        Integer[] innerArray = new Integer[]{10, 20, 30, 40};
        assertArrayEquals(outerArray, innerArray);
    }

    @Test
    public void complexIteration() {
        Iterator<Integer> iterOne = rqInstance.iterator();
        Iterator<Integer> iterTwo = rqInstance.iterator();
        while (iterOne.hasNext()) {
            System.out.println("inside iterator");
            iterOne.next();
        }
        assertTrue(iterTwo.hasNext());
    }
    
    @Test(expected = NullPointerException.class)
    public void enqueueNull() {
        rqInstance.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFromEmpty() {
        int k = rqInstance.size();
        for (int i = 0; i < k + 1; i++) {
            rqInstance.dequeue();
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void sampleFromEmpty() {
        int k = rqInstance.size();
        for (int i = 0; i < k; i++) {
            rqInstance.dequeue();
        }
        rqInstance.sample();
    }
}
