package com.mjc.school.repository.dataWorker;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
