package com.example.demo.Services;

import com.example.demo.DTO.request.CreateNoteRequest;
import com.example.demo.DTO.request.CreateUserRequest;
import com.example.demo.DTO.request.LoginUserRequest;
import com.example.demo.DTO.response.CreateNoteResponse;
import com.example.demo.DTO.response.CreateUserResponse;
import com.example.demo.EXCEPTION.PasswordException;
import com.example.demo.EXCEPTION.UserNotFound;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testThatUserCanBeCreated() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("admin1");
        createUserRequest.setPassword("admin1");

        CreateUserResponse response = userService.createUser(createUserRequest);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("created.OK", response.getMessage());
        assertEquals(1, userService.countNumberOfUser());
    }

    @Test
    void testThatUserCannotBeCreatedWithExistingUsername() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("admin1");
        createUserRequest.setPassword("admin1");

        userService.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = new CreateUserRequest();
        createUserRequest2.setUsername("admin1");
        createUserRequest2.setPassword("admin2");

        assertThrows(UserNotFound.class, () -> userService.createUser(createUserRequest2));
    }

    @Test
    void testThatUserCannotBeCreatedWithExistingUsernameWithDifferentCase() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("ADMIN1");
        createUserRequest.setPassword("admin1");

        userService.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = new CreateUserRequest();
        createUserRequest2.setUsername("admin1");
        createUserRequest2.setPassword("admin1");

        assertThrows(UserNotFound.class, () -> userService.createUser(createUserRequest2));
    }

    @Test
    void testThatUserCanLogin() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("admin1");
        createUserRequest.setPassword("admin1");

        userService.createUser(createUserRequest);

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername(createUserRequest.getUsername());
        loginUserRequest.setPassword(createUserRequest.getPassword());

        User loggedInUser = userService.login(loginUserRequest.getUsername(), loginUserRequest.getPassword());

        assertNotNull(loggedInUser);
        assertEquals("admin1", loggedInUser.getUsername());
    }

    @Test
    void testLoginWithInvalidPassword() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("admin1");
        createUserRequest.setPassword("admin1");

        userService.createUser(createUserRequest);

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("admin1");
        loginUserRequest.setPassword("admin10");

        assertThrows(PasswordException.class, () -> userService.login(loginUserRequest.getUsername(), loginUserRequest.getPassword()));
    }

    @Test
    void testLoginWithNonExistingUser() {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("nonexistent");
        loginUserRequest.setPassword("password");

        assertThrows(UserNotFound.class, () -> userService.login(loginUserRequest.getUsername(), loginUserRequest.getPassword()));
    }

    @Test
    void testThatUserCanCreateNote() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("user1");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userService.createUser(createUserRequest);

        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("Sample Note");
        createNoteRequest.setContent("This is a sample note content.");
        createNoteRequest.setUserId(createUserResponse.getId()); // Optional if the userId is not part of the request
        createNoteRequest.setCreatedAt(LocalDateTime.now());
        createNoteRequest.setAuthor("user1"); // Make sure this matches the username for the note's author

        CreateNoteResponse createNoteResponse = noteService.createNote(createNoteRequest, createUserRequest.getUsername());

        assertNotNull(createNoteResponse);
        assertNotNull(createNoteResponse.getNoteId());
        assertEquals("note.CREATED", createNoteResponse.getMessage());
    }
}
