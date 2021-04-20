package com.training.elnino.exception;

public class DataReaderInputSourceException extends Exception {

    public DataReaderInputSourceException() {
        this(null);
    }

    public DataReaderInputSourceException(String message) {
        super(message);
    }
}
