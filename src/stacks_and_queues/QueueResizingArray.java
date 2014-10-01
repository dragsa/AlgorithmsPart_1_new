/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks_and_queues;

import edu.princeton.cs.introcs.StdOut;
import java.util.Iterator;

/**
 *
 * @author dragsa
 */
public class QueueResizingArray<Item> implements Iterable<Item> {

    private Item[] array;
    private int tail, head;

    public QueueResizingArray() {
        array = (Item[]) new Object[1];
    }
    //create an empty queue

    public void enqueue(Item item) {
        StdOut.println("current size: " + arrayLenght());
        StdOut.println("items in queue: " + size());
        StdOut.println("tail: " + tail);
        StdOut.println("head: " + head);
        StdOut.println("enqueue " + item);
        if (size() == array.length) {
            resize(2 * array.length);
        }
        if (isEmpty()) {
            array[head] = item;
            tail++;
        } else {
            array[tail++] = item;
        }
        StdOut.println("current size: " + arrayLenght());
        StdOut.println("items in queue: " + size());
        StdOut.println("tail: " + tail);
        StdOut.println("head: " + head);
    }
    //insert a new string onto queue

    public Item dequeue() {
        StdOut.println("current size: " + arrayLenght());
        StdOut.println("items in queue: " + size());
        StdOut.println("tail: " + tail);
        StdOut.println("head: " + head);
        if (isEmpty()) {
            throw new RuntimeException("attempt to dequeue on empty queue ");
        }
        Item item = array[head];
        array[head++] = null;
        StdOut.println("dequeue " + item);
        if (size() == array.length / 4) {
            resize(array.length / 2);
        }
        StdOut.println("current size: " + arrayLenght());
        StdOut.println("items in queue: " + size());
        StdOut.println("tail: " + tail);
        StdOut.println("head: " + head);
        return item;
    }
    //remove and return the string least recently added

    private void resize(int capacity) {
        StdOut.println("resize called: from " + array.length + " to " + capacity);
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            newArray[i] = array[i + head];
        }
        array = newArray;
        tail = tail - head;
        head = 0;
    }

    public boolean isEmpty() {
        return array[head] == null;
    }
    //is the queue empty?

    public int size() {
        return tail - head;
    }
    //number of strings on the queue

    public int arrayLenght() {
        return array.length;
    }
    
    public Iterator<Item> iterator() {
        return new QueueResizingArrayIterator();
    }
    
    private class QueueResizingArrayIterator implements Iterator<Item>{
        
        private int iteratorHead = head;
        
        public boolean hasNext() {
            return iteratorHead < tail;
        }
        
        public Item next() {
            return array[iteratorHead++];
        }
        
        public void remove() {
            
        }
    }
}
