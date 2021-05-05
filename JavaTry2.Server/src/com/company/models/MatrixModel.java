package com.company.models;

import java.io.Serializable;

public class MatrixModel implements Serializable {
    private int size;
    private int[][] values;

    public int getSize() {
        return this.size;
    }

    public int getValue(int i, int j) {
        return this.values[i][j];
    }
}
