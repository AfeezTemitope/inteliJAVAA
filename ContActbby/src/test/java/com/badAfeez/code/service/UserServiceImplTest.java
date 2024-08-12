package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.*;
import com.badAfeez.code.DtoBBY.response.*;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;

    @BeforeEach
    void cleanUp(){
        userRepository.deleteAll();
        contactRepository.deleteAll();
    }
//    @AfterEach
//    void clearUp(){
//        contactRepository.deleteAll();
//    }

    @Test
    void testThatUserCanBeCreated_AndCanBeRetrieved(){
        CreateUserRequest request = new CreateUserRequest();
        request.setName("chi");
        request.setEmail("chi@gmail.com");
        request.setPassword("123456");

        CreateUserResponse response = userService.createUser(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("created.OK");

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(request.getName());
        assertThat(user.getEmail()).isEqualTo(request.getEmail());
    }
    @Test
    void testThatUserCanBeCreatedBeforeUserCanLogIn(){
        CreateUserRequest request = new CreateUserRequest();
        request.setName("chi");
        request.setEmail("chi@gmail.com");
        request.setPassword("123456");

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setPhoneNumber(request.getPhoneNumber());
        loginRequest.setPassword(request.getPassword());

        LoginResponse loginResponse = userService.isUserLoggedIn(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("login.OK");

        CreateUserResponse response = userService.createUser(request);
        assertThat(response).isNotNull();
    }
    @Test
    void testThatUserCanAdd_A_Contact(){
        CreateUserRequest request = new CreateUserRequest();
        request.setName("chi");
        request.setEmail("chi@gmail.com");
        request.setPassword("123456");

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setPhoneNumber(request.getPhoneNumber());
        loginRequest.setPassword(request.getPassword());

        LoginResponse loginResponse = userService.isUserLoggedIn(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("login.OK");

        CreateUserResponse response = userService.createUser(request);
        assertThat(response).isNotNull();

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName(request.getName());
        createContactRequest.setPhoneNumber("12345");

        CreateContactResponse contactResponse = contactService.addContact(createContactRequest);
        assertThat(contactResponse.getMessage()).isEqualTo("added contact successfully");
    }
    @Test
    void testThatUserCanAdd2Contacts(){
        CreateUserRequest request = new CreateUserRequest();
        request.setName("chi");
        request.setEmail("chi@gmail.com");
        request.setPassword("123456");

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setPhoneNumber(request.getPhoneNumber());
        loginRequest.setPassword(request.getPassword());

        LoginResponse loginResponse = userService.isUserLoggedIn(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("login.OK");

        CreateUserResponse response = userService.createUser(request);
        assertThat(response).isNotNull();

        CreateContactRequest contactRequest1 = new CreateContactRequest();
        contactRequest1.setName("How to kill a facilitator");
        contactRequest1.setPhoneNumber("12345");
        CreateContactResponse contactResponse1 = contactService.addContact(contactRequest1);
        assertThat(contactResponse1).isNotNull();
        assertThat(contactResponse1.getMessage()).isEqualTo("added contact successfully");

        CreateContactRequest contactRequest2 = new CreateContactRequest();
        contactRequest2.setName("Chibuzor!!!");
        contactRequest2.setPhoneNumber("67890");

        CreateContactResponse contactResponse2 = contactService.addContact(contactRequest2);
        assertThat(contactResponse2).isNotNull();
        assertThat(contactResponse2.getMessage()).isEqualTo("added contact successfully");

        contactRepository.findContactsBy(request.getId());
        assertEquals(2,contactRepository.count());
    }
    @Test
    void testThatUserCanFindContactByPhoneNumber(){
        CreateUserRequest request = new CreateUserRequest();
        request.setName("chi");
        request.setEmail("chi@gmail.com");
        request.setPassword("123456");
        request.setPhoneNumber("12345");

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setPhoneNumber(request.getPhoneNumber());
        loginRequest.setPassword(request.getPassword());

        LoginResponse loginResponse = userService.isUserLoggedIn(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("login.OK");

        CreateUserResponse response = userService.createUser(request);
        assertThat(response).isNotNull();

        CreateContactRequest contactRequest1 = new CreateContactRequest();
        contactRequest1.setName("How to kill a facilitator");
        contactRequest1.setPhoneNumber("12345");
        CreateContactResponse contactResponse1 = contactService.addContact(contactRequest1);
        assertThat(contactResponse1).isNotNull();
        assertThat(contactResponse1.getMessage()).isEqualTo("added contact successfully");

        CreateContactRequest contactRequest2 = new CreateContactRequest();
        contactRequest2.setName("Chibuzor!!!");
        contactRequest2.setPhoneNumber("67890");

        CreateContactResponse contactResponse2 = contactService.addContact(contactRequest2);
        assertThat(contactResponse2).isNotNull();
        assertThat(contactResponse2.getMessage()).isEqualTo("added contact successfully");

        Optional nonExistentContact = contactRepository.findByPhoneNumber("00000");
        assertThat(nonExistentContact).isEmpty();

        Optional existingNumber = contactRepository.findByPhoneNumber("12345");
        assertThat(existingNumber).isNotNull();
    }
//    @Test
//    void testThatUserCanFindAndDeleteContactByPhoneNumber() {
//        CreateUserRequest request = new CreateUserRequest();
//        request.setName("chi");
//        request.setEmail("chi@gmail.com");
//        request.setPassword("123456");
//        request.setPhoneNumber("12345");
//
//        LoginUserRequest loginRequest = new LoginUserRequest();
//        loginRequest.setPhoneNumber(request.getPhoneNumber());
//        loginRequest.setPassword(request.getPassword());
//
//        CreateContactRequest contactRequest1 = new CreateContactRequest();
//        contactRequest1.setName("How to kill a facilitator");
//        contactRequest1.setPhoneNumber("12345");
//
//        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
//        deleteContactRequest.setPhoneNumber("12345");
//
//        LoginResponse loginResponse = userService.isUserLoggedIn(loginRequest);
//        assertThat(loginResponse).isNotNull();
//        assertThat(loginResponse.getMessage()).isEqualTo("login.OK");
//
//        CreateUserResponse createUserResponse = userService.createUser(request);
//        assertThat(createUserResponse).isNotNull();
//
//        CreateContactResponse contactResponse1 = contactService.addContact(contactRequest1);
//        assertThat(contactResponse1).isNotNull();
//        assertThat(contactResponse1.getMessage()).isEqualTo("added contact successfully");
//
//        Optional<Contacts> contact = contactRepository.findByPhoneNumber("12345");
//        assertThat(contact).isPresent();
//        assertThat(contact.get().getPhoneNumber()).isEqualTo("12345");
//
//        DeleteContactResponse deleteContactResponse = contactService.deleteContact(deleteContactRequest);
//        assertThat(deleteContactResponse).isNotNull();
//        assertThat(deleteContactResponse.getMessage()).isEqualTo("DeletedContact.SUCCESSFULLY");
//
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        Optional<Contacts> existingNumber = contactRepository.findByPhoneNumber("12345");
//        assertThat(existingNumber).isEmpty();
//    }

    @Test
    void testThatUserCanUpdateContact(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("chi");
        createUserRequest.setEmail("chi@gmail.com");
        createUserRequest.setPassword("123456");
        createUserRequest.setPhoneNumber("12345");

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("BadBoy");
        createContactRequest.setPhoneNumber("12345");

        UpdateContactRequest updateContactRequest = new UpdateContactRequest();
        updateContactRequest.setOldPhoneNumber("12345");
        updateContactRequest.setNewPhoneNumber("12345");
        updateContactRequest.setNewName("UpdatedBadBoy");

        userService.createUser(createUserRequest);
        contactService.addContact(createContactRequest);

        Optional<Contacts> contacts = contactRepository.findByPhoneNumber("12345");
        assertThat(contacts).isPresent();
        assertThat(contacts.get().getName()).isEqualTo("BadBoy");

        UpdateContactResponse updateContactResponse = contactService.updateContact(updateContactRequest);
        assertThat(updateContactResponse).isNotNull();
        assertThat(updateContactResponse.getMessage()).isEqualTo("updatedContact.SUCCESSFULLY");
    }
    @Test
    void testThatContactCanBeDeleted(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("chi");
        createUserRequest.setEmail("chi@gmail.com");
        createUserRequest.setPassword("123456");
        createUserRequest.setPhoneNumber("12345");

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("BadBoy");
        createContactRequest.setPhoneNumber("12345");

        userService.createUser(createUserRequest);
        contactService.addContact(createContactRequest);
        Optional<Contacts> contacts = contactRepository.findByPhoneNumber("12345");
        assertThat(contacts).isPresent();
        assertThat(contacts.get().getName()).isEqualTo("BadBoy");

        DeleteContactRequest deleteRequest = new DeleteContactRequest();
        deleteRequest.setPhoneNumber(createContactRequest.getPhoneNumber());

        DeleteContactResponse deleteResponse = contactService.deleteContact(deleteRequest);
        assertThat(deleteResponse.getMessage()).isEqualTo("DeletedContact.SUCCESSFULLY");

    }


}