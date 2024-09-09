package com.example.demo.Services;

import com.example.demo.DTO.request.CreateNoteRequest;
import com.example.demo.DTO.response.CreateNoteResponse;
import com.example.demo.EXCEPTION.NoteException;
import com.example.demo.EXCEPTION.UserNotFound;
import com.example.demo.data.models.Note;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.NoteRepository;
import com.example.demo.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private final UserRepository userRepository;

    private void validateNoteTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new NoteException("not cannot be empty");
    }
    private String normalizeNote(String title) {
        return title != null ? title.trim().toLowerCase() : null;
    }

    @Override
    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest, String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new UserNotFound("user not found");

        String title = normalizeNote(createNoteRequest.getTitle());
        validateNoteTitle(title);
        normalizeNote(title);
        Note existingNote = noteRepository.findByTitle(title);
        if (existingNote != null) throw new NoteException("note already exists");
        Note newNote = new Note();
        newNote.setCreatedAt(LocalDateTime.now());
        newNote.setTitle(title);
        newNote.setAuthor(username);
        newNote.setContent(createNoteRequest.getContent());
        newNote.setUser(user);
        newNote = noteRepository.save(newNote);
        CreateNoteResponse createNoteResponse = new CreateNoteResponse();
        createNoteResponse.setUserId(user.getId());
        createNoteResponse.setNoteId(newNote.getId());
        createNoteResponse.setMessage("note.CREATED");
        return createNoteResponse;
    }
}
