package com.example.demo.EXCEPTION;

public class PasswordException extends RuntimeException {
    public PasswordException(String passwordIsEmpty) {
        super(passwordIsEmpty);
    }
}
