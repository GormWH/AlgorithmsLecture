/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int elementNum = 0;
        String champion = "";
        while (!StdIn.isEmpty()) {
            String element = StdIn.readString();
            elementNum++;
            if (StdRandom.bernoulli(1 / (double) elementNum)) {
                champion = element;
            }
        }
        System.out.println(champion);
    }
}
