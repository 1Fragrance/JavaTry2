package common.models;

import java.io.Serializable;

public class MatrixModel implements Serializable {
    private final int size;
    private final double[][] values;

    public MatrixModel(int size) {
        this.size = size;
        values = new double[size][size];
    }

    public int getSize() {
        return this.size;
    }

    public double getValue(int i, int j) {
        return this.values[i][j];
    }

    public double[][] getValues() {
        return this.values;
    }

    public void setValue(int i, int j, double value) {
        values[i][j] = value;
    }
}
