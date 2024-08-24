package com.badAfeez.code.Exception;

public class ContactNotFound extends RuntimeException{
    public ContactNotFound(String message){
        super(message);
    }
}
