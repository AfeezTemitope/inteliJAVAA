package com.badAfeez.code.Exceptions;

public class NoteNotFound extends RuntimeException{
    public NoteNotFound(){
        super("Note not found");
    }
}
