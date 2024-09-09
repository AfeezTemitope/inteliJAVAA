package com.example.contact.Services;

import com.example.contact.DTos.request.CreateUserRequest;
import com.example.contact.DTos.request.LoginUserRequest;
import com.example.contact.DTos.response.AddContactResponse;
import com.example.contact.DTos.response.CreateUserResponse;
import com.example.contact.DTos.request.AddContactRequest;
import com.example.contact.DTos.response.LoginUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServiceTest {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserServices userServices;

    private String loggedInUserId;

    @Test
    void testThatUserCanLogin(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("badamba");
        createUserRequest.setPassword("admin123");
        CreateUserResponse response = userServices.registerUser(createUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("badamba");
        loginUserRequest.setPassword("admin123");
        userServices.login(loginUserRequest);
        loggedInUserId = String.valueOf(response.getUserId());
        System.out.println(loggedInUserId);
    }
    @Test
    void testThatUserCanAddContact(){
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("badamba");
        loginUserRequest.setPassword("admin123");
        userServices.login(loginUserRequest);
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setUserId(loggedInUserId);
        addContactRequest.setContactName("bby chi");
        addContactRequest.setContactNumber("12345678909");
        AddContactResponse contactResponse = contactService.addContact(addContactRequest);
        contactResponse.setMessage("created.OK");


    }

}