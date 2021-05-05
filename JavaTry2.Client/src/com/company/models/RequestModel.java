package com.company.models;

import com.company.enums.Operation;

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
}
