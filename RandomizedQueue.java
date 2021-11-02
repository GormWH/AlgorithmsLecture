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
    private Item[] items;
    private int size = 0;
    private int maxSize = 1;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // resize when array lacks capacity
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
        maxSize = capacity;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("enqueue(): called with null argument");
        if (size == maxSize) {
            resize(size * 2);
        }
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException("dequeue(): queue is empty");
        int random = StdRandom.uniform(0, size);
        Item item = items[random];
        items[random] = items[--size];
        items[size] = null;
        if (size > 0 && size == maxSize / 4) resize(maxSize / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException("dequeue(): queue is empty");
        int random = StdRandom.uniform(0, size);
        return items[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int itemsLeft = size;

        public boolean hasNext() {
            return itemsLeft > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("iterator.remove(): not supported");
        }

        public Item next() {
            if (hasNext()) {
                int random = StdRandom.uniform(0, itemsLeft);
                Item item = items[random];
                if (random != itemsLeft - 1) {
                    items[random] = items[--itemsLeft];
                    items[itemsLeft] = item;
                }
                else {
                    itemsLeft--;
                }
                return item;
            }
            else {
                throw new NoSuchElementException("iterator.next(): no more items in iteration");
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> sample = new RandomizedQueue<Integer>();
        StdOut.println(sample.size() + " " + sample.maxSize);
        sample.enqueue(1);
        StdOut.println(sample.size() + " " + sample.maxSize);
        sample.enqueue(2);
        StdOut.println(sample.size() + " " + sample.maxSize);
        sample.enqueue(3);
        StdOut.println(sample.size() + " " + sample.maxSize);
        sample.enqueue(4);
        StdOut.println(sample.size() + " " + sample.maxSize);
        sample.enqueue(5);
        StdOut.println(sample.size() + " " + sample.maxSize);
        for (int integer : sample) {
            StdOut.println(integer);
        }
    }

}
