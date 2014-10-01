/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author dragsa
 */
public class JDequeTest {

    int size = 2;
    int last = 12;
    Deque<Integer> dequeuInstance;

    //execute for each test, before executing test
    @Before
    public void before() {
        System.out.println("in before");
        dequeuInstance = new Deque<Integer>();
        dequeuInstance.addFirst(10);
        dequeuInstance.addFirst(12);
    }

    //execute for each test, after executing test
    @After
    public void after() {
        System.out.println("in after");
    }

    @Test
    public void testSimpleDequeLast() {
        assertEquals(last, (int) dequeuInstance.removeLast());
    }

    @Test
    public void testSimpleDequeSize() {
        assertEquals(size, dequeuInstance.size());
    }
}
