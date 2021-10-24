/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> /*implements Iterable<Item>*/ {
    // declaration of subclass Node
    private class Node {
        Item item;
        Node next;
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
    public void addFirst(Item item) IllegalArgumentException {
        if (isEmpty()) {
            Node oldfirst = first;
            first = new Node();
            first.next = oldfirst;
        }
        else {
            first = new Node();
            last = first;
        }
        first.item = item;
        StdOut.println(first.item);
        StdOut.println(last.item);
    }

    // add the item to the back
    public void addLast(Item item) throws IllegalArgumentException {

    }

    // remove and return the item from the front
    // public Item removeFirst()

    // remove and return the item from the back
    // public Item removeLast()

    // return an iterator over items in order from front to back
    // public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> intDeque = new Deque<Integer>();
        intDeque.addFirst(1);
    }

}
