package com.badAfeez.code.service;

import com.badAfeez.code.Exceptions.NoteNotFound;
import com.badAfeez.code.data.models.Note;
import com.badAfeez.code.data.repository.NoteRepository;
import com.badAfeez.code.dtObby.request.CreateNoteRequest;
import com.badAfeez.code.dtObby.request.UpdateNoteRequest;
import com.badAfeez.code.dtObby.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class NoteServiceImL implements NoteServices{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Long countNumberOfNote() {
     return noteRepository.count();

    }

    @Override
    public CreateNoteResponse createNote(CreateNoteRequest request) {
        Note note = new Note();
        note.setNoteContent(request.getNoteContent());
        note.setNoteTitle(request.getNoteTitle());
        note.setNoteDateTimeCreatedAt(LocalDateTime.now());
        Note saved = noteRepository.save(note);

        CreateNoteResponse response = new CreateNoteResponse();
        response.setMessage("Note created successfully");
        response.setNoteId(saved.getNoteId());
        return response;

    }

    @Override
    public UpdateNoteResponse updateNote(UpdateNoteRequest updateRequest)  {
       Note note = noteRepository.findBy().orElseThrow(()->new NoteNotFound());
       note.setNoteTitle(updateRequest.getNoteTitle());
       note.setNoteContent(updateRequest.getNoteContent());
       noteRepository.save(note);
       UpdateNoteResponse response = new UpdateNoteResponse();
       response.setMessage("Note updated successfully");
       return response;
    }

    @Override
    public DeleteNoteResponse deleteNote(String noteId) {
        noteRepository.deleteById(noteId);
        DeleteNoteResponse response = new DeleteNoteResponse();
        response.setMessage("Note deleted successfully");
        return response;
    }

    @Override
    public GetNoteResponse getNoteId(String noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFound());
        GetNoteResponse response = new GetNoteResponse();
        response.setNoteId(note.getNoteId());
        response.setNoteTitle(note.getNoteTitle());
        response.setNoteContent(note.getNoteContent());
        return response;
    }


}



