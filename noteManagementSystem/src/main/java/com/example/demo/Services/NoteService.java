package com.example.demo.Services;

import com.example.demo.DTO.request.CreateNoteRequest;
import com.example.demo.DTO.response.CreateNoteResponse;

public interface NoteService {

    CreateNoteResponse createNote(CreateNoteRequest createNoteRequest, String username);
}
