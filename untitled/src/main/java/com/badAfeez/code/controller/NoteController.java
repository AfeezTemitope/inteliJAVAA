package com.badAfeez.code.controller;

import com.badAfeez.code.dtObby.request.CreateNoteRequest;
import com.badAfeez.code.dtObby.response.CreateNoteResponse;
import com.badAfeez.code.service.NoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteServices noteServices;

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest request) {
        CreateNoteResponse response = noteServices.createNote(request);
        return ResponseEntity.ok(response);
    }
}
