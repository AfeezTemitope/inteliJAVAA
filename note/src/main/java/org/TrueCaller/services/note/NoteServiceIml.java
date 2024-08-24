package org.TrueCaller.services.note;

import org.TrueCaller.data.model.Note;
import org.TrueCaller.data.repository.NoteRepository;
import org.TrueCaller.dtos.request.CreateNoteRequest;
import org.TrueCaller.dtos.response.CreateNoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteServiceIml implements NoteService{
    
    @Autowired
    private NoteRepository noteRepository;


    @Override
    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
        Note newNote = new Note();
        newNote.setNoteContent(createNoteRequest.getNoteContent());
        newNote.setNoteTitle(createNoteRequest.getNoteTitle());
        newNote.setNoteDateTimeCreatedAt(LocalDateTime.now());
        noteRepository.save(newNote);
        CreateNoteResponse createNoteResponse = new CreateNoteResponse();
        createNoteResponse.setMessage("Note created succesfully");
        return createNoteResponse;
    }

    @Override
    public Long countNumberOfNote() {
        return  noteRepository.count();
    }
}
