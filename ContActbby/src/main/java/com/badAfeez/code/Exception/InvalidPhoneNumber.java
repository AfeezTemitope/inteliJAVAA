package com.badAfeez.code.Exception;

public class InvalidPhoneNumber extends RuntimeException {
    public InvalidPhoneNumber() {
        super("Invalid phone number");
    }
}
