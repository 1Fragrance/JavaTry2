package common.models;

import common.enums.Operation;

import java.io.Serializable;

public class RequestModel implements Serializable {
    MatrixModel matrix;
    Operation operation;

    public RequestModel(MatrixModel matrix) {
        this.matrix = matrix;
        operation = Operation.CalculateDeterminant;
    }

    public RequestModel() {
        operation = Operation.Exit;
        matrix = null;
    }

    public Operation getOperation() {
        return operation;
    }

    public MatrixModel getMatrix() {
        return matrix;
    }
}
