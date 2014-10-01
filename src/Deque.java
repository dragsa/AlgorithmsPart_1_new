
import java.util.Iterator;
import java.util.NoSuchElementException;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dragsa
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    public Deque() {
    }
    // construct an empty deque

//Throw a NullPointerException if the client attempts to add a null item;
//throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
//throw an UnsupportedOperationException if the client calls the remove() method in the iterator;
    public boolean isEmpty() {
        return size == 0;
    }
    // is the deque empty?

    public int size() {
        return size;
    }
    // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }
    // insert the item at the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            last.prev = oldLast;
            oldLast.next = last;
        }
        size++;
    }
    // insert the item at the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = first;
        } else {
            first.prev = null;
        }      
        return item;
    }
    // delete and return the item at the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }      
        return item;
    }
    // delete and return the item at the end

    public Iterator<Item> iterator() {
        return new DequeDoubleLinkedListIterator();
    }
    // return an iterator over items in order from front to end

    private class Node {

        Item item;
        Node next;
        Node prev;
    }

    private class DequeDoubleLinkedListIterator implements Iterator<Item> {

        private Node firstIterator = first;

        public boolean hasNext() {
            return firstIterator != null;
        }

        public Item next() {
            //throw a java.util.NoSuchElementException if the client calls the next() method in the iterator 
//and there are no more items to return.
            Item currentItem = (Item) firstIterator.item;
            firstIterator = firstIterator.next;
            return currentItem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
    }
    // unit testing
}
