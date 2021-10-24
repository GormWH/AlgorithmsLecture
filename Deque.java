/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node before;
        Node next;
    }

    private Node first = null;
    private Node last = null;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        int size = 0;
        Node cur = first;
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        first.before = new Node();
        first = first.before;
        first.item = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        last.next = new Node();
        last.next.before = last;
        last = last.next;
        last.item = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        first.before = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = last.item;
        last = last.before;
        last.next = null;
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

        public Item next() {
            try {
                Item item = current.item;
                current = current.next;
                return item;
            }
            catch (NoSuchElementException e) {
                StdOut.println("null access of next() of iterator");
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> integerDeque = new Deque<Integer>();
        integerDeque.addFirst(1);
        integerDeque.addFirst(2);
        for (int integer : integerDeque)
            StdOut.println(integer);
        StdOut.println(integerDeque.size());
    }

}
