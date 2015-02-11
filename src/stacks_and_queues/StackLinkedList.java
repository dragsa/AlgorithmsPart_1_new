/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks_and_queues;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 *
 * @author dragsa
 */
public class StackLinkedList<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    public StackLinkedList() {
    }
    //create an empty stack

    void push(Item item) {
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = first;
        first = newFirst;
        size++;
    }
    //insert a new string onto stack

    public Item pop() {
        if (size() == 0) {
            throw new EmptyStackException();
        }
        Item item = (Item) first.item;
        first = first.next;
        size--;
        return item;
    }
    //remove and return the string most recently added

    public boolean isEmpty() {
        return first == null;
    }
    //is the stack empty?

    public int size() {
        return size;
    }
    //number of strings on the stack

    public Iterator<Item> iterator() {
        return new StackLinkedListIterator();
    }
    
    private class StackLinkedListIterator implements Iterator<Item> {
        
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
