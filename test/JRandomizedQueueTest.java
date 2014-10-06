
import java.util.NoSuchElementException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import stacks_and_queues.QueueLinkedList;

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
        assertEquals(3, rqInstance.size());
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
    public void testSample() {
        for (int i = 0; i < 3; i++) {
            System.out.println("new cycle");
            int k = rqInstance.size();
            for (int j = 0; j < k; j++) {
                System.out.println(rqInstance.sample());
            }
            assertEquals(3, rqInstance.size());
        }
    }

    @Ignore
    @Test
    public void simpleIteration() {
    }

    @Ignore
    @Test
    public void complexIteration() {
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
