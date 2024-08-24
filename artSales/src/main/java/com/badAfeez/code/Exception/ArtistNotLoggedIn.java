package com.badAfeez.code.Exception;

public class ArtistNotLoggedIn extends RuntimeException{
    public ArtistNotLoggedIn(String message){
        super(message);
    }
}
