/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // declaration of subclass Node
    private class Node {
        private Item item = null;
        private Node next = null;
        private Node before = null;
    }

    // instances
    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty deque
    // public Deque()

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        try {
            if (!isEmpty()) {
                Node oldfirst = first;
                first = new Node();
                oldfirst.before = first;
                first.next = oldfirst;
            }
            else {
                first = new Node();
                last = first;
            }
            first.item = item;
            size++;
        }
        catch (IllegalArgumentException e) {
            // StdOut.print("null access on addFirst()");
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        try {
            if (!isEmpty()) {
                Node oldlast = last;
                last.next = new Node();
                last = last.next;
                last.before = oldlast;
            }
            else {
                last = new Node();
                first = last;
            }
            last.item = item;
            size++;
        }
        catch (IllegalArgumentException e) {
            // StdOut.print("null access on addLast()");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        try {
            Item item = first.item;
            first = first.next;
            first.before = null;
            size--;
            return item;
        }
        catch (NoSuchElementException e) {
            // StdOut.println("null access on removeFirst");
            return null;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        try {
            Item item = last.item;
            last = last.before;
            last.next = null;
            size--;
            return item;
        }
        catch (NoSuchElementException e) {
            // StdOut.println("null access on removeLast");
            return null;
        }
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
        // Deque<Integer> intDeque = new Deque<Integer>();

        // StdOut.println("Checking add method");
        // StdOut.println("Expecting 3 2 1 4 5");
        // intDeque.addFirst(1);
        // intDeque.addFirst(2);
        // intDeque.addFirst(3);
        // intDeque.addLast(4);
        // intDeque.addLast(5);
        // for (int item : intDeque) {
        //     StdOut.println(item);
        // }
        // StdOut.println(intDeque.size());

        // StdOut.println("Checking remove method");
        // StdOut.println("removeFirst() " + intDeque.removeFirst());
        // StdOut.println("removeLast() " + intDeque.removeLast());
        // for (int item : intDeque) {
        //     StdOut.println(item);
        // }
        // StdOut.println(intDeque.size());
    }

}
