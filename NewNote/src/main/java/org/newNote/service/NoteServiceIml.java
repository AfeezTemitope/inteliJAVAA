package org.newNote.service;

import org.newNote.Exception.NoteNotFound;
import org.newNote.data.models.Note;
import org.newNote.data.repository.NoteRepository;
import org.newNote.dtoSbby.request.CreateNoteRequest;
import org.newNote.dtoSbby.request.DeleteNoteRequest;
import org.newNote.dtoSbby.request.UpdateNoteRequest;
import org.newNote.dtoSbby.response.CreateNoteResponse;
import org.newNote.dtoSbby.response.DeleteNoteResponse;
import org.newNote.dtoSbby.response.UpdateNoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteServiceIml implements NoteService{

    @Autowired
    private NoteRepository noteRepository;



    @Override
    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
    Note note = new Note();
    note.setNoteContent(createNoteRequest.getNoteContent());
    note.setNoteTitle(createNoteRequest.getNoteTitle());
    note.setNoteDateTimeCreatedAt(LocalDateTime.now());
    noteRepository.save(note);
    CreateNoteResponse response = new CreateNoteResponse();
    response.setMessage("Note created successfully");
    response.setNoteId(note.getNoteId());
        return response;
    }
    @Override
    public UpdateNoteResponse updateNote(UpdateNoteRequest request) {
        Note note = noteRepository.findById(request.getNoteId()).orElseThrow(()->new NoteNotFound("Note not found"));
        note.setNoteTitle(request.getNoteTitle());
        note.setNoteContent(request.getNoteContent());
        note.setNoteDateTimeCreatedAt(LocalDateTime.now());
        noteRepository.save(note);
        UpdateNoteResponse response = new UpdateNoteResponse();
        response.setMessage("Note updated successfully");
        return response;
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest request) {
        Note note = noteRepository.findByTitle(request.getNoteTitle()).orElseThrow(()-> new NoteNotFound("note not found"));
        noteRepository.delete(note);
        DeleteNoteResponse response = new DeleteNoteResponse();
        response.setMessage("Note deleted successfully");
        return response;
    }


    @Override
    public Long countNumberOfNote() {
        return noteRepository.count();
    }


}
