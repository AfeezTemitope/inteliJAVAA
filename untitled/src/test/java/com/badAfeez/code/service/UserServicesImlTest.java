package com.badAfeez.code.service;

import com.badAfeez.code.data.models.Users;
import com.badAfeez.code.data.repository.UsersRepository;
import com.badAfeez.code.dtObby.request.*;
import com.badAfeez.code.dtObby.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImlTest {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private NoteServices noteServices;

    @BeforeEach
    void cleanUp(){
        usersRepository.deleteAll();
    }

    @Test
    void testThatUserCanCreateNewUser(){
        CreateUserRequest createNewUser = new CreateUserRequest();
        createNewUser.setName("IAmBad");
        createNewUser.setEmail("fareedah is a fool");
        createNewUser.setPhoneNumber("123456789");
        createNewUser.setPassword("qaz");

        CreateUserResponse createUserResponse = userServices.createNewUser(createNewUser);
        assertThat(createUserResponse).isNotNull();
        assertEquals(1, userServices.countNumberOfUsers());
        assertThat(createUserResponse.getMessage()).contains("Successfully created user");
    }

    @Test
    void testThatUserCanViewNote(){

        CreateUserRequest createNewUser = new CreateUserRequest();
        createNewUser.setName("IAmBad");
        createNewUser.setEmail("fareedah is a fool");
        createNewUser.setPhoneNumber("123456789");
        createNewUser.setPassword("qaz");

        CreateUserResponse createUserResponse = userServices.createNewUser(createNewUser);
        assertThat(createUserResponse).isNotNull();

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setEmail(createNewUser.getEmail());
        loginRequest.setPassword(createNewUser.getPassword());

        userServices.loginUser(loginRequest);

        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteTitle("Na me go kill chi sha");
        createNoteRequest.setNoteContent("how to kill chi 102");
        CreateNoteResponse createNoteResponse = noteServices.createNote(createNoteRequest);

        GetNoteResponse getNoteResponse = noteServices.getNoteId(String.valueOf(createNoteResponse.getNoteId()));
        assertThat(getNoteResponse).isNotNull();
        assertEquals(createNoteResponse.getNoteId(), getNoteResponse.getNoteId());
        assertEquals(createNoteRequest.getNoteTitle(), getNoteResponse.getNoteTitle());
        assertEquals(createNoteRequest.getNoteContent(), getNoteResponse.getNoteContent());
    }

    @Test
    void testThatUserCanEditNote(){
        CreateUserRequest createNewUser = new CreateUserRequest();
        createNewUser.setName("IAmBad");
        createNewUser.setEmail("fareedah is a fool");
        createNewUser.setPhoneNumber("123456789");
        createNewUser.setPassword("qaz");
        CreateUserResponse createUserResponse = userServices.createNewUser(createNewUser);
        assertThat(createUserResponse).isNotNull();

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setEmail(createNewUser.getEmail());
        loginRequest.setPassword(createNewUser.getPassword());
        userServices.loginUser(loginRequest);

        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteTitle("Na me go kill chi sha");
        createNoteRequest.setNoteContent("how to kill chi 102");
        CreateNoteResponse createNoteResponse = noteServices.createNote(createNoteRequest);

        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setNoteId(createNoteResponse.getNoteId());
        updateNoteRequest.setNoteTitle("Na me go kill chi sha");
        updateNoteRequest.setNoteContent("how to kill chi 102 updated");
        UpdateNoteResponse updateNoteResponse = noteServices.updateNote(updateNoteRequest);

        assertThat(updateNoteResponse).isNotNull();
        assertEquals(updateNoteRequest.getNoteId(), updateNoteResponse.getNoteId());
        assertEquals(updateNoteRequest.getNoteTitle(), updateNoteResponse.getNoteTitle());
        assertEquals(updateNoteRequest.getNoteContent(), updateNoteResponse.getNoteContent());
    }


}









//        shareNote
//deleteNote

