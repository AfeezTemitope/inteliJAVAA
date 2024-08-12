package org.newNote.service;

import org.newNote.dtoSbby.request.CreateNoteRequest;
import org.newNote.dtoSbby.request.DeleteNoteRequest;
import org.newNote.dtoSbby.request.UpdateNoteRequest;
import org.newNote.dtoSbby.response.CreateNoteResponse;
import org.newNote.dtoSbby.response.DeleteNoteResponse;
import org.newNote.dtoSbby.response.UpdateNoteResponse;

public interface NoteService {
    CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);

    Long countNumberOfNote();

    UpdateNoteResponse updateNote(UpdateNoteRequest request);

    DeleteNoteResponse deleteNote(DeleteNoteRequest request);
}
