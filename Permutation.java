/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> sample = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            sample.enqueue(word);
        }
        while (k > 0) {
            StdOut.println(sample.dequeue());
            k--;
        }
    }
}
