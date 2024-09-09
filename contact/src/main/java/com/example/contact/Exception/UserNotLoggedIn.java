package com.example.contact.Exception;

public class UserNotLoggedIn extends RuntimeException{
    public UserNotLoggedIn(String s){
        super(s);
    }
}
