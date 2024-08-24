package org.newNote.Exception;

public class NoteNotFound extends RuntimeException {
    public NoteNotFound(String noteNotFound) {
        super(noteNotFound);
    }
}
