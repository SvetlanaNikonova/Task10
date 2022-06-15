package com.coherentsolutions.training.aqa.java.web.nikonova.parser;

public class NoSuchFileException extends RuntimeException {

    public NoSuchFileException() {

    }

    public NoSuchFileException(String message, Throwable t) {
        super(message, t);
    }
}
