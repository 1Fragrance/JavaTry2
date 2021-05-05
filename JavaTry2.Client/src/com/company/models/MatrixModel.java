package com.company.models;

import java.io.Serializable;

public class MatrixModel implements Serializable {
    private final int size;
    private final int[][] values;

    public MatrixModel(int size) {
        this.size = size;
        values = new int[size][size];
    }

    public int getSize() {
        return this.size;
    }

    public int getValue(int i, int j) {
        return this.values[i][j];
    }

    public void setValue(int i, int j, int value) {
        values[i][j] = value;
    }
}
