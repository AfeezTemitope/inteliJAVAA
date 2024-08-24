package com.badAfeez.code.Exception;

public class UserFound extends RuntimeException {
    public UserFound() {
        super("user already existing");
    }
}
