/* *****************************************************************************
 *  Name:              SuHong Park
 *  Coursera User ID:  4kidsp@naver.com
 *  Last modified:     November 02, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // declaration of subclass Node
    private class Node {
        private Item item;
        private Node next = null;
        private Node prev = null;

        public Node(Item newItem) {
            if (newItem != null) {
                item = newItem;
            }
            else {
                item = null;
            }
        }
    }

    // class instances of RandomizedQueue
    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

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
        if (item == null)
            throw new IllegalArgumentException("enqueue(Item item): called with null argument");
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

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("dequeue(): no more element in queue");
        }
        Item item;
        if (size == 1) {
            item = first.item;
            first = null;
            last = null;
        }
        else {
            Node cur = first;
            int random = StdRandom.uniform(0, size);
            for (int i = 0; i < random; i++) {
                cur = cur.next;
            }
            item = cur.item;
            if (cur.prev != null)
                cur.prev.next = cur.next;
            if (cur.next != null)
                cur.next.prev = cur.prev;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("sample(): no more element in queue");
        }
        Item item;
        Node cur = first;
        int random = StdRandom.uniform(0, size);
        for (int i = 0; i < random; i++) {
            cur = cur.next;
        }
        item = cur.item;
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int itemsLeft = size;

        public boolean hasNext() {
            return itemsLeft > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("iterator.remove(): not supported");
        }

        public Item next() {
            if (hasNext()) {
                // get random item
                int random = StdRandom.uniform(0, itemsLeft);
                Node cur = first;
                for (int i = 0; i < random; i++) {
                    cur = cur.next;
                }
                Item item = cur.item;

                // remove cur from queue and enqueue it to the last
                if (random == 0) {
                    if (size == 1) {
                        first = null;
                        last = null;
                    }
                    else {
                        first = first.next;
                        first.prev = null;
                    }
                }
                else if (random == size - 1) {
                    last = last.prev;
                    last.next = null;
                }
                else {
                    cur.next.prev = cur.prev;
                    cur.prev.next = cur.next;
                }
                size--;
                itemsLeft--;
                enqueue(item);

                // return item
                return item;

            }
            else {
                throw new NoSuchElementException("iterator.next(): no more items in iteration");
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
