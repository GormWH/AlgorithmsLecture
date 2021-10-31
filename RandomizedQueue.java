/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // class instances
    private ArrayList<Item> items = new ArrayList<Item>();

    // construct an empty randomized queue
    // public RandomizedQueue()

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return items.size();
    }

    // add the item
    public void enqueue(Item item) {
        try {
            items.add(item);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("enqueue() was called with null argument");
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.size() != 0) {
            int random = StdRandom.uniform(0, this.size());
            return items.remove(random);
        }
        else {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.size() != 0) {
            int random = StdRandom.uniform(0, this.size());
            Item item = items.get(random);
            return item;
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
        int itemsLeft = items.size();

        public boolean hasNext() {
            return itemsLeft != 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("iterator.remove() not supported");
        }

        public Item next() {
            if (hasNext()) {
                int random = StdRandom.uniform(0, itemsLeft);
                Item item = items.get(random);
                items.add(items.remove(random));
                itemsLeft--;
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
            RandomizedQueue<Integer> sample = new RandomizedQueue<Integer>();
            for (int i = 1; i <= k; i++) {
                sample.enqueue(i);
            }
            for (int integer : sample) {
                System.out.println(integer);
            }
        }
    }

}
