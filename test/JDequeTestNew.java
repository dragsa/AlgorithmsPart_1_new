/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author dragsa
 */
public class JDequeTestNew {

    int size = 3;
    int last = 12;
    Deque<Integer> dequeuInstance;

    //execute for each test, before executing test
    @Before
    public void before() {
        System.out.println("in before NEW");
        dequeuInstance = new Deque<Integer>();
        dequeuInstance.addFirst(12);
        dequeuInstance.addFirst(10);
    }

    //execute for each test, after executing test
    @After
    public void after() {
        System.out.println("in after NEW");
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
