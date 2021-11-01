/* *****************************************************************************
 *  Name:              SuHong Park
 *  Coursera User ID:  4kidsp@naver.com
 *  Last modified:     November 02, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
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

    // class instances of Deque
    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("addFirst(Item item): called with null argument");
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        }
        else {
            Node oldfirst = first;
            first = new Node(item);
            oldfirst.prev = first;
            first.next = oldfirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("addLast(Item item): called with null argument");
        if (isEmpty()) {
            last = new Node(item);
            first = last;
        }
        else {
            Node oldlast = last;
            last = new Node(item);
            oldlast.next = last;
            last.prev = oldlast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("removeFirst(): Deque is empty");
        Item item = first.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("removeLast(): Deque is empty");
        Item item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("iterator.remove(): not supported");
        }

        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else {
                throw new NoSuchElementException("iterator.next(): no more items in iteration");
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> intDeque = new Deque<Integer>();

        StdOut.println("Checking add method");
        StdOut.println("Expecting 3 2 1 4 5");
        intDeque.addFirst(1);
        intDeque.addFirst(2);
        intDeque.addFirst(3);
        intDeque.addLast(4);
        intDeque.addLast(5);
        for (int item : intDeque) {
            StdOut.println(item);
        }
        StdOut.println("size is " + intDeque.size());

        StdOut.println("Checking remove method");
        StdOut.println("removeFirst() " + intDeque.removeFirst());
        StdOut.println("removeLast() " + intDeque.removeLast());
        for (int item : intDeque) {
            StdOut.println(item);
        }
        StdOut.println("size is " + intDeque.size());
    }

}
