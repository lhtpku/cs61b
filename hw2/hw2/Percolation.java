package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] status; // status if block is open
    private int[][] grid;
    private int head;
    private int tail;
    private int numberOfOpenSites;
    private int size;
    private WeightedQuickUnionUF WQU;
    private WeightedQuickUnionUF backWash;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        head = N * N;
        tail = head + 1;
        grid = new int[N][N];

        status = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                status[i][j] = false;
            }
        }

        numberOfOpenSites = 0;
        WQU = new WeightedQuickUnionUF(tail + 1);
        backWash = new WeightedQuickUnionUF(tail);
    }

    private void checkBound(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size)  {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private int corToNum(int row, int col) {
        return row * size + col;
    }

    public boolean isOpen(int row, int col) {
        checkBound(row, col);
        return status[row][col];
    }

    public void open(int row, int col) {
        checkBound(row, col);
        if (isOpen(row, col)) {
            return;
        }
        status[row][col] = true;
        numberOfOpenSites += 1;
        int num = corToNum(row, col);

        if (row == 0) {
            WQU.union(head, num);
        } else if (row == size - 1) {
            WQU.union(num, tail);
        }

        if (col < size - 1 && isOpen(row, col + 1)) {
            WQU.union(num, corToNum(row, col + 1));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            WQU.union(num, corToNum(row, col - 1));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            WQU.union(num, corToNum(row - 1, col));
        }
        // down
        if (row < size - 1 && isOpen(row + 1, col)) {
            WQU.union(num, corToNum(row + 1, col));
        }
    }

    public boolean isFull(int row, int col) {
        checkBound(row, col);
        return (status[row][col] && WQU.connected(head, corToNum(row, col)));
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return WQU.connected(head, tail);
    }

    public static void main(String[] args) {
        ;
    }
}