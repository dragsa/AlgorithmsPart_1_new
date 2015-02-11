/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks_and_queues;

import edu.princeton.cs.introcs.StdOut;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 *
 * @author dragsa
 */
public class StackResizingArray<Item> implements Iterable<Item> {
    
	private Item[] array;
    private int pointer;

    public StackResizingArray() {
        array = (Item[]) new Object[1];
    }
    //create an empty stack

    public void push(Item item) {
        if (pointer == array.length) {
            resize(2 * array.length);
        }
        array[pointer++] = item;
    }
    //insert a new string onto stack

    public Item pop() {
        if (size() == 0) {
            throw new EmptyStackException();
        }
        Item item = array[--pointer];
        array[pointer] = null;
        if (pointer > 0 && pointer == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }
    //remove and return the string most recently added

    private void resize(int capacity) {
        StdOut.println("resize called: from " + pointer + " to " + capacity);
        Item[] newArray = (Item[]) new Object[capacity];
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
    
    public Iterator<Item> iterator() {
        return new StackResizingArrayIterator();
    }
    
    private class StackResizingArrayIterator implements Iterator<Item>{
        
        private int iteratorPointer = pointer;
        
        public boolean hasNext() {
            return iteratorPointer > 0;
        }
        
        public Item next() {
            return array[--iteratorPointer];
        }
        
        public void remove() {
            
        }
    }
}
