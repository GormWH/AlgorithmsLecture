import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // subclass Node
    private class Node {
        private Item item;
        private Node prev = null;
        private Node next = null;

        public Node(Item newItem) {
            if (newItem == null) {
                item = null;
            }
            else {
                item = newItem;
            }
        }
    }

    // class instances of RandomizedQueue
    private Node first = null;
    private Node last = null;
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
            if (isEmpty()) {
                first = new Node(item);
                last = first;
            }
            else {
                last.next = new Node(item);
                last.next.prev = last;
                last = last.next;
            }
            size++;
        }
        catch (IllegalArgumentException e) {
            // StdOut.println("null access on enque()");
        }
    }

    // remove and return a random item
    public Item dequeue() {
        try {
            if (size == 0) {
                throw new NoSuchElementException("dequeue(): no more element in queue");
            }
            else if (size == 1) {
                Item item = first.item;
                first = null;
                last = null;
                size--;
                return item;
            }
            else {
                int randomNumber = StdRandom.uniform(0, size);
                Node cur = first;
                for (int i = 0; i < randomNumber; i++) {
                    cur = cur.next;
                }
                Item item = cur.item;
                if (randomNumber != 0 && randomNumber != size - 1) {
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                }
                else if (randomNumber == 0) {
                    first = cur.next;
                    first.prev = null;
                }
                else {
                    last = last.prev;
                    last.next = null;
                }
                size--;
                return item;
            }
        }
        catch (NoSuchElementException e) {
            // StdOut.println("null access on dequeue(): no more element in queue");
            return null;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        try {
            if (size == 0) {
                throw new NoSuchElementException("dequeue(): no more element in queue");
            }
            else {
                int randomNumber = StdRandom.uniform(0, size);
                Node cur = first;
                for (int i = 0; i < randomNumber; i++) {
                    cur = cur.next;
                }
                return cur.item;
            }
        }
        catch (NoSuchElementException e) {
            // StdOut.println("null access on dequeue(): no more element in queue");
            return null;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int itemsLeft = size;

        public boolean hasNext() {
            return itemsLeft != 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("iterator.remove() not supported");
        }

        public Item next() {
            if (hasNext()) {
                Node cur = first;
                int randomNumber = StdRandom.uniform(0, itemsLeft);
                for (int i = 0; i < randomNumber; i++) {
                    cur = cur.next;
                }
                Item item = cur.item;
                if (randomNumber == 0) {
                    cur.next.prev = null;
                    first = cur.next;
                }
                else if (randomNumber == size - 1) {
                    cur.prev.next = null;
                    last = cur.prev;
                }
                else {
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                }
                size--;
                itemsLeft--;
                enqueue(item);
                return item;
            }
            else {
                throw new NoSuchElementException("null access in iterator.next()");
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        intQueue.enqueue(4);
        intQueue.enqueue(5);
        for (int integer : intQueue) {
            StdOut.println(integer);
        }
    }

}
