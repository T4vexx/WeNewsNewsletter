package com.otavio.wenews.exceptions;

public class NonexistentPostError extends Exception {

    public NonexistentPostError(String message) {
        super(message);
    }
}
