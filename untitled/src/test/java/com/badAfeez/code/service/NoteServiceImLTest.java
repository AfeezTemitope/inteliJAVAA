package com.badAfeez.code.service;

import com.badAfeez.code.data.repository.NoteRepository;
import com.badAfeez.code.dtObby.request.CreateNoteRequest;
import com.badAfeez.code.dtObby.request.UpdateNoteRequest;
import com.badAfeez.code.dtObby.response.CreateNoteResponse;
import com.badAfeez.code.dtObby.response.DeleteNoteResponse;
import com.badAfeez.code.dtObby.response.UpdateNoteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoteServiceImLTest {
    @Autowired
    private NoteServices noteServices;

    @Autowired
    private NoteRepository noteRepository;

        @BeforeEach
        void cleanUp(){
        noteRepository.deleteAll();
        }

    @Test
    void testThatNoteCanBeCreated(){
        CreateNoteRequest request = new CreateNoteRequest();
        request.setNoteTitle("chi omo weray");
        request.setNoteContent("why this chi");
        request.setDateAndTimeCreated(LocalDateTime.now());

        CreateNoteResponse response = noteServices.createNote(request);
        assertThat(response).isNotNull();
        assertEquals(1,noteServices.countNumberOfNote());
        assertThat(response.getMessage()).contains("Note created successfully");
    }

    @Test
    void testThatMultipleNotesCanBeAdded(){
        CreateNoteRequest request1 = new CreateNoteRequest();
        request1.setNoteTitle("chi omo weray");
        request1.setNoteContent("why this chi");
        request1.setDateAndTimeCreated(LocalDateTime.now());
        noteServices.createNote(request1);

        CreateNoteRequest request2 = new CreateNoteRequest();
        request2.setNoteTitle("another note");
        request2.setNoteContent("another content");
        request2.setDateAndTimeCreated(LocalDateTime.now());
        noteServices.createNote(request2);


        assertEquals(2, noteServices.countNumberOfNote());
    }

    @Test
    void testThatNoteCanAddMultipleNotesAndDelete(){
            CreateNoteRequest request1 = new CreateNoteRequest();
            request1.setNoteTitle("chi omo weray");
            request1.setNoteContent("why this chi");
            request1.setDateAndTimeCreated(LocalDateTime.now());
            noteServices.createNote(request1);

            CreateNoteRequest request2 = new CreateNoteRequest();
            request2.setNoteTitle("another note");
            request2.setNoteContent("another content");
            request2.setDateAndTimeCreated(LocalDateTime.now());
            noteServices.createNote(request2);

            assertEquals(2, noteServices.countNumberOfNote());
            DeleteNoteResponse deleteNoteResponse = noteServices.deleteNote(request1.getNoteId());
            assertThat(deleteNoteResponse).isNotNull();
            assertEquals(1,noteServices.countNumberOfNote());
            assertThat(deleteNoteResponse.getMessage()).contains("Note deleted successfully");

    }
    @Test
    void testThatNoteCanBeUpdated(){
            CreateNoteRequest request = new CreateNoteRequest();
            request.setNoteTitle("chi omo weray");
            request.setNoteContent("why this chi");
            request.setDateAndTimeCreated(LocalDateTime.now());

            CreateNoteResponse response = noteServices.createNote(request);
            UpdateNoteRequest updateRequest = new UpdateNoteRequest();
            updateRequest.setNoteTitle("chi u do this wan");
            updateRequest.setNoteContent("1 - 0");
            updateRequest.setNoteId(response.getNoteId());

            UpdateNoteResponse updateNoteResponse = noteServices.updateNote(updateRequest);

            assertEquals(1,noteServices.countNumberOfNote());
            assertThat(updateNoteResponse.getMessage()).contains("Note updated successfully");

    }

    @Test
    void testthatNoteCanBeDeleted(){
            CreateNoteRequest request = new CreateNoteRequest();
            request.setNoteTitle("chi omo weray");
            request.setNoteContent("why this chi");
            request.setDateAndTimeCreated(LocalDateTime.now());

            CreateNoteResponse response = noteServices.createNote(request);

            DeleteNoteResponse deleteNoteResponse = noteServices.deleteNote(response.getNoteId());
            assertThat(response).isNotNull();
            assertEquals(0,noteServices.countNumberOfNote());
            assertThat(deleteNoteResponse.getMessage()).contains("Note deleted successfully");
    }

}