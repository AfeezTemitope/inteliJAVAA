package com.example.demo.EXCEPTION;

public class UserMustNotBeEmpty extends RuntimeException {
    public UserMustNotBeEmpty(String usernameIsEmpty) {
        super(usernameIsEmpty);
    }
}
