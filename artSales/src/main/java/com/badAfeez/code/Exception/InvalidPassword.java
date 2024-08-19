package com.badAfeez.code.Exception;

public class InvalidPassword extends RuntimeException{
    public InvalidPassword(){
        super("Invalid Password");
    }
}
