/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks_and_queues;

import java.util.EmptyStackException;

/**
 *
 * @author dragsa
 */
public class StackOfStringsLinkedList {

    private Node first;
    private int size;

    public StackOfStringsLinkedList() {
    }
    //create an empty stack

    void push(String item) {
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = first;
        first = newFirst;
        size++;
    }
    //insert a new string onto stack

    public String pop() {
        if (size() == 0) {
            throw new EmptyStackException();
        }
        String item = first.item;
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

    private class Node {

        String item;
        Node next;
    }
}
