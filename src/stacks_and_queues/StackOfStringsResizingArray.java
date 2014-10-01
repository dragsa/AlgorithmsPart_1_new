/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks_and_queues;

import edu.princeton.cs.introcs.StdOut;
import java.util.EmptyStackException;

/**
 *
 * @author dragsa
 */
public class StackOfStringsResizingArray {

    private String[] array;
    private int pointer;

    public StackOfStringsResizingArray() {
        array = new String[1];
    }
    //create an empty stack

    void push(String item) {
        if (pointer == array.length) {
            resize(2 * array.length);
        }
        array[pointer++] = item;
    }
//insert a new string onto stack

    public String pop() {
        if (size() == 0) {
            throw new EmptyStackException();
        }
        String item = array[--pointer];
        array[pointer] = null;
        if (pointer > 0 && pointer == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }
//remove and return the string most recently added

    private void resize(int capacity) {
        StdOut.println("resize called: from " + pointer + " to " + capacity);
        String[] newArray = new String[capacity];
        for (int i = 0; i < pointer; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        StdOut.println("current size: " + array.length);
        StdOut.println("items in stack: " + size());
    }

    public boolean isEmpty() {
        return pointer == 0;
    }
//is the stack empty?

    public int size() {
        return pointer;
    }
    //number of strings on the stack
    
    public int arrayLenght() {
        return array.length;
    }
}
