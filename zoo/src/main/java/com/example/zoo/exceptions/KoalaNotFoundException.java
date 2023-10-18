package com.example.zoo.exceptions;



public class KoalaNotFoundException extends RuntimeException {
    public KoalaNotFoundException(String message) {
        super(message);
    }
}
