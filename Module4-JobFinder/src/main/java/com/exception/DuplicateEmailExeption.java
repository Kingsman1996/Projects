package com.exception;

public class DuplicateEmailExeption extends RuntimeException {
    public DuplicateEmailExeption(String message) {
        super(message);
    }
}
