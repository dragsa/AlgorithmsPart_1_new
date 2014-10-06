/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author dragsa
 */
public class JDequeTest {

    Deque<Integer> dequeuInstance;

    //execute for each test, before executing test
    @Before
    public void before() {
        System.out.println("in before");
        dequeuInstance = new Deque<Integer>();
        dequeuInstance.addFirst(10);
        dequeuInstance.addFirst(20);
        dequeuInstance.addFirst(30);
    }

    //execute for each test, after executing test
    @After
    public void after() {
        System.out.println("in after");
        dequeuInstance = null;
    }

    @Test
    public void testRemoveLast() {
        assertEquals(10, (int) dequeuInstance.removeLast());
    }

    @Test
    public void testRemoveFirst() {
        assertEquals(30, (int) dequeuInstance.removeFirst());
    }

    @Test
    public void testAddLast() {
        dequeuInstance.addLast(40);
        assertEquals(40, (int) dequeuInstance.removeLast());
    }

    @Test
    public void testAddFirst() {
        dequeuInstance.addFirst(0);
        assertEquals(0, (int) dequeuInstance.removeFirst());
    }

    @Test
    public void testDequeSize() {
        assertEquals(3, dequeuInstance.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastFromEmpty() {
        int k = dequeuInstance.size();
        for (int i = 0; i < k + 1; i++) {
            dequeuInstance.removeLast();
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmpty() {
        int k = dequeuInstance.size();
        for (int i = 0; i < k + 1; i++) {
            dequeuInstance.removeFirst();
        }
    }

    @Test(expected = NullPointerException.class)
    public void addingNullAsFirst() {
        dequeuInstance.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void addingNullAsLast() {
        dequeuInstance.addLast(null);
    }

    @Test
    public void simpleIteration() {
        int[] expected = new int[]{30, 20, 10};
        int index = 0;
        for (Integer currentInt : dequeuInstance) {
            assertEquals(new Integer(expected[index]), currentInt);
            index++;
        }
    }

    @Test
    public void complexIteration() {
        int[] expected = new int[]{30, 20, 10};
        int index = 0;
        int indexInner = 0;
        for (Integer currentInt : dequeuInstance) {
            assertEquals(new Integer(expected[index]), currentInt);
            for (Integer currentIntInner : dequeuInstance) {
                assertEquals(new Integer(expected[indexInner]), currentIntInner);
                indexInner++;
            }
            indexInner = 0;
            index++;
        }
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void interationExceptionRemove() {
        dequeuInstance.iterator().remove();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void interationExceptionEmpty() {
        int k = dequeuInstance.size();
        for (int i = 0; i < k; i++) {
            dequeuInstance.removeFirst();
        }
        dequeuInstance.iterator().next();
    }
}
