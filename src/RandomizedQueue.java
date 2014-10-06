
import java.util.Arrays;
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
public class RandomizedQueue<Item> implements Iterable<Item> {

//throw a NullPointerException if the client attempts to add a null item;
//throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue;
//throw an UnsupportedOperationException if the client calls the remove() method in the iterator;
//throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and
//there are no more items to return
    private Item[] array;
    private int tail, head;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }
    // construct an empty randomized queue

    public boolean isEmpty() {
        return array[head] == null;
    }
    // is the queue empty?

    public int size() {
        return tail - head;
    }
    // return the number of items on the queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size() == array.length) {
            resize(2 * array.length);
        }
        if (isEmpty()) {
            array[head] = item;
            tail++;
        } else {
            array[tail++] = item;
        }
    }
    // add the item

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = randomizer();
        Item item = array[index];
        if (index != tail - 1) {
            array[index] = array[tail - 1];
        }
        array[--tail] = null;
        if (size() == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }
    // delete and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = randomizer();
        return array[index];
    }
    // return (but do not delete) a random item

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        tail = tail - head;
    }

    private int randomizer() {
        return StdRandom.uniform(size());
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    // return an independent iterator over items in random order

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] iteratorArrayCopy;
        private int iteratorHead;
        private int iteratorTail;

        public RandomizedQueueIterator() {
            iteratorArrayCopy = Arrays.copyOf(array, size());
            StdRandom.shuffle(iteratorArrayCopy);
            iteratorTail = iteratorArrayCopy.length;
        }

        public boolean hasNext() {
            return iteratorHead < iteratorTail;
        }

        public Item next() {
            if (iteratorArrayCopy[iteratorHead] == null) {
                throw new NoSuchElementException();
            }
            return iteratorArrayCopy[iteratorHead++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
    }
    // unit testing
}
