/* *****************************************************************************
 *  Name:              SuHong Park
 *  Coursera User ID:  4kidsp@naver.com
 *  Last modified:     November 02, 2021
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
        for (String word : sample) {
            StdOut.println(word);
            k--;
            if (k <= 0) break;
        }
    }
}
