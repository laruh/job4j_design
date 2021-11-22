package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        this.row = nextRow(0);
    }

    private int nextRow(int start) {
        int rslRow = -1;
        for (int i = start; i < data.length; i++) {
            if (data[i].length > 0) {
                rslRow = i;
                break;
            }
        }
        return rslRow;
    }

    @Override
    public boolean hasNext() {
        return row != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length) {
            row = nextRow(++row);
            column = 0;
        }
        return data[row][column++];
    }
}