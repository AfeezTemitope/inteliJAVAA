package org.newNote.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newNote.data.repository.NoteRepository;
import org.newNote.dtoSbby.request.CreateNoteRequest;
import org.newNote.dtoSbby.request.DeleteNoteRequest;
import org.newNote.dtoSbby.request.UpdateNoteRequest;
import org.newNote.dtoSbby.response.CreateNoteResponse;
import org.newNote.dtoSbby.response.DeleteNoteResponse;
import org.newNote.dtoSbby.response.UpdateNoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteServiceImlTest {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @BeforeEach
    void cleanUp(){
        noteRepository.deleteAll();
    }

    @Test
    void testThatNoteCanBeCreated(){
        CreateNoteRequest request = new CreateNoteRequest();
        request.setNoteTitle("chibuzor is evil");
        request.setNoteContent("100 ways to kill chi");
        request.setDateAndTimeCreated(LocalDateTime.now());

        CreateNoteResponse response = noteService.createNote(request);
        assertThat(response).isNotNull();
        assertEquals(1, noteService.countNumberOfNote());
        assertThat(response.getMessage()).contains("Note created successfully");
    }

    @Test
    void testThatNoteCanBeUpdated(){
        UpdateNoteRequest request = new UpdateNoteRequest();
        request.setNoteTitle("chibuzor is evil");
        request.setNoteContent("100 ways to kill chiBuzorma");
        request.setDateAndTimeCreated(LocalDateTime.now());
        UpdateNoteResponse response = noteService.updateNote(request);
        assertThat(response).isNotNull();
        assertEquals(1, noteService.countNumberOfNote());
        assertThat(response.getMessage()).contains("Note updated successfully");
    }

    @Test
    void testThatNoteCanBeDeleted(){
        CreateNoteRequest request = new CreateNoteRequest();
        request.setNoteTitle("chibuzor is evil");
        request.setNoteContent("100 ways to kill chiBuzorma");
        request.setDateAndTimeCreated(LocalDateTime.now());

        CreateNoteResponse response = noteService.createNote(request);
        assertThat(response).isNotNull();
        assertEquals(1, noteService.countNumberOfNote());
        assertThat(response.getMessage()).contains("Note created successfully");

        DeleteNoteRequest deleteRequest = new DeleteNoteRequest();
        deleteRequest.setNoteTitle("chibuzor is evil");

        DeleteNoteResponse deleteResponse = noteService.deleteNote(deleteRequest);
        assertThat(deleteResponse).isNotNull();
        assertEquals(0, noteService.countNumberOfNote());
        assertThat(deleteResponse.getMessage()).contains("Note deleted successfully");
        }
}