package com.mjc.school.repository.exceptions;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
