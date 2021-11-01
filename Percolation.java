/* *****************************************************************************
 *  Name:              SuHong Park
 *  Coursera User ID:  4kidsp@naver.com
 *  Last modified:     October 15, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private boolean[] stat;
    private final int size, length;
    private int openedNum;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Percolation(int n): n <= 0");
        }
        length = n;
        size = n * n;
        uf = new WeightedQuickUnionUF(size + 2);
        stat = new boolean[size + 2];

        // initialize to blocked (except virtual top and bottom)
        for (int i = 0; i < size; i++) {
            stat[i] = false;
        }
        stat[size] = true;   // virtual top
        stat[size + 1] = false; // virtual bottom
        openedNum = 0;

        // connect upper and bottom sites
        /*
        for (int i = 0; i < n; i++) {
            uf.union(i, size);
            uf.union(size - n + i, size + 1);
        }
        */
    }

    // interprets 2D -> 1D
    private int from2dTo1d(int row, int col) {
        return (row - 1) * length + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > length || col < 1 || col > length) {
            throw new IllegalArgumentException(
                    "Percolation.open(int row, int col): (row,col) out of range");
        }
        int id = from2dTo1d(row, col);
        if (!stat[id]) {
            stat[id] = true;
            openedNum++;
            // connect to virtual top, bottom
            if (row == 1) uf.union(id, size);
            if (row == length) uf.union(id, size + 1);

            // connect to neighboring open sites
            if (!(row <= 1) && isOpen(row - 1, col)) uf.union(id, id - length);
            if (!(row >= length) && isOpen(row + 1, col)) uf.union(id, id + length);
            if (!(col <= 1) && isOpen(row, col - 1)) uf.union(id, id - 1);
            if (!(col >= length) && isOpen(row, col + 1)) uf.union(id, id + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > length || col < 1 || col > length) {
            throw new IllegalArgumentException(
                    "Percolation.isOpen(int row, int col): (row,col) out of range");
        }
        int id = from2dTo1d(row, col);
        return stat[id];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > length || col < 1 || col > length) {
            throw new IllegalArgumentException(
                    "Percolation.isFull(int row, int col): (row,col) out of range");
        }
        int id = from2dTo1d(row, col);
        return uf.find(id) == uf.find(size);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedNum;
    }

    // does the system percolate?
    public boolean percolates() {
        /*
        for (int i = 1; i <= length; i++) {
            if (isFull(length, i)) return true;
        }
        return false;
        */
        return uf.find(size) == uf.find(size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation per = new Percolation(5);
        per.open(1, 1);
        per.open(2, 1);
        per.open(2, 2);
        per.open(3, 2);
        per.open(4, 2);
        per.open(4, 3);
        per.open(5, 2);
        per.open(5, 5);
        System.out.println(per.isFull(4, 3));
        System.out.println(per.isFull(5, 5));
        System.out.println(per.percolates());
    }
}
