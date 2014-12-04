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
public class SymbolTableSequentailSearch<Key, Value> {

    void put(Key key, Value val) {
    }
//put key-value pair into the table (remove key from table if value is null )

    Value get(Key key) {
        return null;
    }
//value paired with key (null  if key is absent)

    void delete(Key key) {
    }
//remove key (and its value) from table

    boolean contains(Key key) {
        return false;
    }
//is there a value paired with key?

    boolean isEmpty() {
        return false;
    }
//is the table empty?

    int size() {
        return 0;
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
    }
}
