package com.badAfeez.code.Exception;

public class ArtistNotFound extends RuntimeException{
    public ArtistNotFound(){
        super("Artist Not Found");
    }
}
