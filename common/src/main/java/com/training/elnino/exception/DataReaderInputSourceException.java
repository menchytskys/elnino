package com.training.elnino.exception;

public class DataReaderInputSourceException extends RuntimeException {

    public DataReaderInputSourceException() {
        this(null);
    }

    public DataReaderInputSourceException(String message) {
        super(message);
    }
}
