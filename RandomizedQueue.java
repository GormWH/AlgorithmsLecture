/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // subclass Node
    private class Node {
        private Item item;
        private Node next;
        private Node before;
    }

    // class instances
    private Node first = null;
    private int size = 0;

    // construct an empty randomized queue
    // public RandomizedQueue()

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        try {
            if (first != null) {
                Node oldfirst = first;
                first = new Node();
                first.item = item;
                first.next = oldfirst;
                oldfirst.before = first;
                size++;
            }
            else {
                first = new Node();
                first.item = item;
                size++;
            }
        }
        catch (IllegalArgumentException e) {
            StdOut.println("enqueue() was called with null argument");
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (size != 0) {
            int random = StdRandom.uniform(0, size);
            Node current = first;
            for (int i = 0; i <= random; i++) {
                current = current.next;
            }
            if (current.before != null) {
                current.before.next = current.next;
            }
            if (current.next != null) {
                current.next.before = current.before;
            }
            size--;
            return current.item;
        }
        else {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size != 0) {
            int random = StdRandom.uniform(0, size);
            Node current = first;
            for (int i = 0; i <= random; i++) {
                current = current.next;
            }
            return current.item;
        }
        else {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("iterator.remove() not supported");
        }

        public Item next() {
            if (current != null) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else {
                throw new NoSuchElementException("null access in iterator.next()");
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Input one integer as command-line argument");
        }
        else {
            int k = Integer.parseInt(args[0]);
        }
    }

}
