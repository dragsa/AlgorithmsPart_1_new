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
public class QueueLinkedList<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    public QueueLinkedList() {
    }
    //create an empty queue

    public void enqueue(Item item) {
        StdOut.println("items in queue: " + size());
        StdOut.println("enqueue " + item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
        StdOut.println("items in queue: " + size());
    }
    //insert a new string onto queue

    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("attempt to dequeue on empty queue ");
        }
        Item item = first.item;
        StdOut.println("items in queue: " + size());
        StdOut.println("dequeue " + item);
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        StdOut.println("items in queue: " + size());
        return item;
    }
    //remove and return the string least recently added

    public boolean isEmpty() {
        return first == null;
    }
    //is the queue empty?

    public int size() {
        return size;
    }
    //number of strings on the queue

    public Iterator<Item> iterator() {
        return new QueueLinkedListIterator();
    }

    private class QueueLinkedListIterator implements Iterator<Item> {

        private Node firstIterator = first;

        public boolean hasNext() {
            return firstIterator != null;
        }

        public Item next() {
            Item currentItem = (Item) firstIterator.item;
            firstIterator = firstIterator.next;
            return currentItem;
        }

        public void remove() {
        }
    }

    private class Node {

        Item item;
        Node next;
    }
}
