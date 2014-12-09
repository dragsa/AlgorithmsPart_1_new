/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package st;

import stacks_and_queues.QueueOfStringsLinkedList;

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
            boolean found = false;
            while (pointer != null) {
                if (pointer.key.equals(key)) {
                    pointer.value = val;
                    found = true;
                    break;
                }
                pointer = pointer.next;
            }
            if (!found) {
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
    }
//put key-value pair into the table (remove key from table if value is null )

    Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        Node pointer = tail;
        Value valToReturn = null;
        while (pointer != null) {
            if (pointer.key.equals(key)) {
                valToReturn = pointer.value;
            }
            pointer = pointer.next;
        }
        return valToReturn;
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
                    tail.prev = null;
                    pointer = null;
                    return;
                }
                if (pointer.next != null) {
                    pointer.next.prev = pointer.prev;
                } else {
                    pointer.prev.next = null;
                    pointer = null;
                    return;
                }
                if (pointer.prev != null) {
                    pointer.prev.next = pointer.next;
                }
                pointer = null;
                return;
            }
            pointer = pointer.next;
        }
    }
//remove key (and its value) from table

    boolean contains(Key key) {
        return false;
    }
//is there a value paired with key?

    boolean isEmpty() {
        return (tail == null);
    }
//is the table empty?

    int size() {
        return size;
    }
//number of key-value pairs in the table

    Iterable<Key> keys() {
        return null;
    }
//all the keys in the table

    private class Node {

        Key key;
        Value value;
        Node next;
        Node prev;
    }
}
