/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks_and_queues;

/**
 *
 * @author dragsa
 */
public class QueueOfStringsLinkedList {

    private Node first, last;
    private int size;
    
    public QueueOfStringsLinkedList() {
    }
    //create an empty queue

    public void enqueue(String item) {
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
    }
    //insert a new string onto queue

    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("attempt to dequeue on empty queue ");
        }
        String item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
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
    private class Node {

        String item;
        Node next;
    }
}
