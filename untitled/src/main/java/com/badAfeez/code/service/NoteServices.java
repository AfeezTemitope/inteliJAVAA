package com.badAfeez.code.service;

import com.badAfeez.code.dtObby.request.CreateNoteRequest;
import com.badAfeez.code.dtObby.request.UpdateNoteRequest;
import com.badAfeez.code.dtObby.response.*;

public interface NoteServices {
    Long countNumberOfNote();

    CreateNoteResponse createNote(CreateNoteRequest request);

    UpdateNoteResponse updateNote(UpdateNoteRequest updateRequest);

    DeleteNoteResponse deleteNote(String noteId);

    GetNoteResponse getNoteId(String noteId);

//    EditNoteResponse editNote(EditNoteRequest editNoteRequest);
}
