package com.company.models;

import com.company.enums.Operation;

import java.io.Serializable;

public class RequestModel implements Serializable {
    private MatrixModel matrix;
    private Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public MatrixModel getMatrix() {
        return matrix;
    }
}
