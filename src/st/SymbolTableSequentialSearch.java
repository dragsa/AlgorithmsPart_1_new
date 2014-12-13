/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package st;

import java.util.Iterator;
import stacks_and_queues.QueueOfStringsLinkedList;
import stacks_and_queues.StackLinkedList;

/**
 *
 * @author admin
 */
public class SymbolTableSequentialSearch<Key, Value> {

    private int size;
    private Node tail;
//    private Node head;

    void put(Key key, Value val) {
        if (isEmpty()) {
            tail = new Node();
            tail.key = key;
            tail.value = val;
            size++;
//            head = tail;
        } else {
            Node pointer = tail;
            while (pointer != null) {
                if (pointer.key.equals(key)) {
                    pointer.value = val;
                    return;
                }
                pointer = pointer.next;
            }
            Node oldTail = tail;
            tail = new Node();
            tail.key = key;
            tail.value = val;
            tail.next = oldTail;
            oldTail.prev = tail;
//                oldTail = null;
            size++;
        }
    }
//put key-value pair into the table (remove key from table if value is null )

    Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        Node pointer = tail;
        while (pointer != null) {
            if (pointer.key.equals(key)) {
                return pointer.value;
            }
            pointer = pointer.next;
        }
        return null;
    }
//value paired with key (null  if key is absent)

    void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        Node pointer = tail;
        while (pointer != null) {
            if (pointer.key.equals(key)) {
                if (pointer == tail) {
                    tail = pointer.next;
                    if (tail != null) {
                        tail.prev = null;
                    }
                    pointer = null;
                    size--;
                    return;
                }
                if (pointer.next != null) {
                    pointer.next.prev = pointer.prev;
                } else {
                    pointer.prev.next = null;
                    pointer = null;
                    size--;
                    return;
                }
                if (pointer.prev != null) {
                    pointer.prev.next = pointer.next;
                }
                pointer = null;
                size--;
                return;
            }
            pointer = pointer.next;
        }
    }
//remove key (and its value) from table

    boolean contains(Key key) {
        if (isEmpty()) {
            return false;
        }
        Node pointer = tail;
        while (pointer != null) {
            if (pointer.key.equals(key)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }
//is there a value paired with key?

    boolean isEmpty() {
        return (size == 0);
    }
//is the table empty?

    int size() {
        return size;
    }
//number of key-value pairs in the table

    Iterable<Key> keys() {
        return new STLinkedListIterator();
    }
//all the keys in the table
  
//    public Iterator<Key> iterator() {
//        return new STLinkedListIterator();
//    }
    
    private class Node {

        Key key;
        Value value;
        Node next;
        Node prev;
    }
    
    private class STLinkedListIterator implements Iterator, Iterable {
        
        private Node tailIterator = tail;
        
        public boolean hasNext() {
            return tailIterator != null;
        }
        
        public Key next() {
            Key currentKey = (Key) tailIterator.key;
            tailIterator = tailIterator.next;
            return currentKey;
        }
        
        public void remove() {          
        }
        
         public Iterator<Key> iterator() {
        return this;
    }
    }
}
