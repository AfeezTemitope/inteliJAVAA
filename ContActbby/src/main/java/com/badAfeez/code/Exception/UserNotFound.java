package com.badAfeez.code.Exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(){
        super("User not found");
    }
}
