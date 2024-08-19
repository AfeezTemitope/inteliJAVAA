package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.*;
import com.badAfeez.code.DtoBBY.response.*;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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


    @Test
    void testThatUserCanBeCreated_AndCanBeRetrieved(){
    CreateUserRequest request = new CreateUserRequest();
    request.setName("chi");
    request.setEmail("chi@gmail.com");
    request.setPassword("123456");
    request.setPhoneNumber("1234567890");

    CreateUserResponse response = userService.createUser(request);
    assertThat(response).isNotNull();
    assertThat(response.getMessage()).isEqualTo("created.SUCCESSFULLY");

    Optional<User> user = userRepository.findByPhoneNumber(request.getPhoneNumber());
    assertThat(user).isPresent();
    assertThat(user.get().getName()).isEqualTo(request.getName());
    assertThat(user.get().getEmail()).isEqualTo(request.getEmail());
}

    @Test
    void testThatUserCanAddContact() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setName("chi");
        userRequest.setEmail("chi@gmail.com");
        userRequest.setPassword("123456");
        userRequest.setPhoneNumber("1234567890");

        CreateUserResponse userResponse = userService.createUser(userRequest);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isNotNull();

        assertThat(userResponse.getId()).isNotEmpty();

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("amara");
        createContactRequest.setPhoneNumber("54321");
        createContactRequest.setEmail("amaraBby@gmail.com");
        createContactRequest.setUserId(userResponse.getId());

        CreateContactResponse contactResponse = contactService.addContact(createContactRequest);
        assertThat(contactResponse).isNotNull();
        assertThat(contactResponse.getMessage()).isEqualTo("added contact successfully");

        Optional<Contacts> existingContact = contactRepository.findByPhoneNumber("54321");
        assertThat(existingContact).isPresent();
        if (existingContact.isEmpty()) {
            System.out.println("Contact with phone number '54321' was not found.");
        } else {
            System.out.println("Existing contact found: " + existingContact.get());
        }
    }
    @Test
    void testThatUserCanBeCreatedBeforeUserCanLogIn(){
        CreateUserRequest request = new CreateUserRequest();
        request.setName("chi");
        request.setEmail("chi@gmail.com");
        request.setPassword("123456");
        request.setPhoneNumber("1234567890");

        CreateUserResponse response = userService.createUser(request);
        assertThat(response).isNotNull();

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setPhoneNumber(request.getPhoneNumber());
        loginRequest.setPassword(request.getPassword());

        LoginResponse loginResponse = userService.isUserLoggedIn(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("login.SUCCESSFUL");
    }

    @Test
    void testThatUserCanAdd_A_ContactAfterLogin() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setName("chi");
        userRequest.setEmail("chi@gmail.com");
        userRequest.setPassword("123456");
        userRequest.setPhoneNumber("1234567890");

        CreateUserResponse userResponse = userService.createUser(userRequest);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isNotNull();

        Optional<User> user = userRepository.findById(userResponse.getId());
        assertThat(user).isPresent();
        assertThat(user.get().getName()).isEqualTo(userRequest.getName());

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setPhoneNumber(userRequest.getPhoneNumber());
        loginRequest.setPassword(userRequest.getPassword());

        LoginResponse loginResponse = userService.isUserLoggedIn(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("login.SUCCESSFUL");

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("ContactName");
        createContactRequest.setPhoneNumber("54321");
        createContactRequest.setUserId(userResponse.getId());

        CreateContactResponse contactResponse = contactService.addContact(createContactRequest);
        assertThat(contactResponse).isNotNull();
        assertThat(contactResponse.getMessage()).isEqualTo("added contact successfully");

        Optional<Contacts> nonExistentContact = contactRepository.findByPhoneNumber("00000");
        assertThat(nonExistentContact).isEmpty();

        Optional<Contacts> existingContact = contactRepository.findByPhoneNumber("54321");
        assertThat(existingContact).isPresent();
        assertThat(existingContact.get().getPhoneNumber()).isEqualTo("54321");
    }
    @Test
    void testThatUserCanUpdateContactInfo() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setName("chi");
        userRequest.setEmail("chi@gmail.com");
        userRequest.setPassword("123456");
        userRequest.setPhoneNumber("1234567890");


        CreateUserResponse response = userService.createUser(userRequest);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();


        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("chiBby");
        createContactRequest.setPhoneNumber("54321");
        createContactRequest.setEmail("chibby@gmail.com");
        createContactRequest.setUserId(response.getId());

        CreateContactResponse contactResponse = contactService.addContact(createContactRequest);
        assertThat(contactResponse).isNotNull();
        assertThat(contactResponse.getMessage()).isEqualTo("added contact successfully");

        Optional<Contacts> existingContact = contactRepository.findByPhoneNumber("54321");
        assertThat(existingContact).isPresent();

        UpdateContactRequest updateContactRequest = new UpdateContactRequest();
        updateContactRequest.setId(existingContact.get().getId());
        updateContactRequest.setOldPhoneNumber("54321");
        updateContactRequest.setNewName("chiBaby");
        updateContactRequest.setNewPhoneNumber("12345");
        updateContactRequest.setUserId(createContactRequest.getUserId());

        UpdateContactResponse updateContactResponse = contactService.updateContact(updateContactRequest);
        assertThat(updateContactResponse).isNotNull();
        assertThat(updateContactResponse.getMessage()).isEqualTo("updatedContact.SUCCESSFULLY");

        Optional<Contacts> updatedContact = contactRepository.findByPhoneNumber("12345");
        assertThat(updatedContact).isPresent();

        assertThat(updatedContact.get().getPhoneNumber()).isEqualTo("12345");
        assertThat(updatedContact.get().getName()).isEqualTo("chiBaby");
    }

    @Test
    void testToGetContactsForTwoUsers(){
    CreateUserRequest userRequest = new CreateUserRequest();
    userRequest.setName("chi");
    userRequest.setEmail("chi@gmail.com");
    userRequest.setPassword("123456");
    userRequest.setPhoneNumber("1234567890");
   // userRequest.setId("122");

    CreateUserResponse userResponse = userService.createUser(userRequest);
    assertThat(userResponse).isNotNull();
    assertThat(userResponse.getId()).isNotNull();

    CreateUserRequest userRequest2 = new CreateUserRequest();
    userRequest2.setName("Chiq");
    userRequest2.setEmail("chiq@gmail.com");
    userRequest2.setPassword("123456");
    userRequest2.setPhoneNumber("123456789");
    //userRequest2.setId("123");

    CreateUserResponse userResponse1 = userService.createUser(userRequest2);
    assertThat(userResponse1).isNotNull();
    assertThat(userResponse1.getId()).isNotNull();

    CreateContactRequest createContactRequest = new CreateContactRequest();
    createContactRequest.setName("BabyBad");
    createContactRequest.setPhoneNumber("54321");
    createContactRequest.setEmail("badBBy@gmail.com");
    createContactRequest.setUserId(userResponse.getId());

    CreateContactRequest createContactRequest2 = new CreateContactRequest();
    createContactRequest2.setName("BabyBad2");
    createContactRequest2.setPhoneNumber("5432");
    createContactRequest2.setEmail("badBy@gmail.com");
    createContactRequest2.setUserId(userResponse1.getId());
    contactService.addContact(createContactRequest);
    contactService.addContact(createContactRequest2);



    CreateContactRequest createContactRequest3 = new CreateContactRequest();
    createContactRequest3.setName("BabyBad3");
    createContactRequest3.setPhoneNumber("5432");
    createContactRequest3.setEmail("badBBy@gmail.com");
    createContactRequest3
            .setUserId(userResponse.getId());

    CreateContactRequest createContactRequest4 = new CreateContactRequest();
    createContactRequest4.setName("BabyBad4");
    createContactRequest4.setPhoneNumber("54321");
    createContactRequest4.setEmail("badBy@gmail.com");
    createContactRequest4.setUserId(userResponse1.getId());
    contactService.addContact(createContactRequest3);
    contactService.addContact(createContactRequest4);

        List<Contacts> contactForUser1 = contactService.getUserContacts(userResponse.getId());
        assertThat(contactForUser1).isNotNull();
        assertThat(contactForUser1.size()).isEqualTo(2);
        System.out.println(contactForUser1);

        List<Contacts> contactForUser2 = contactService.getUserContacts(userResponse1.getId());
        assertThat(contactForUser2).isNotNull();
        assertThat(contactForUser2.size()).isEqualTo(2);
        System.out.println(contactForUser2);


    }
    @Test
    void testThatUserCanDeleteContact() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setName("chi");
        userRequest.setEmail("chi@gmail.com");
        userRequest.setPassword("123456");
        userRequest.setPhoneNumber("1234567890");

        CreateUserResponse userResponse = userService.createUser(userRequest);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isNotNull();

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("ContactName");
        createContactRequest.setPhoneNumber("54321");
        createContactRequest.setEmail("contact@gmail.com");
        createContactRequest.setUserId(userResponse.getId());

        CreateContactResponse contactResponse = contactService.addContact(createContactRequest);
        assertThat(contactResponse).isNotNull();
        assertThat(contactResponse.getMessage()).isEqualTo("added contact successfully");

        Optional<Contacts> existingContact = contactRepository.findByPhoneNumber("54321");
        assertThat(existingContact).isPresent();

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setPhoneNumber("54321");
        deleteContactRequest.setName("ContactName");
        deleteContactRequest.setUserId(userResponse.getId());

        DeleteContactResponse deleteContactResponse = contactService.deleteContact(deleteContactRequest);
        assertThat(deleteContactResponse).isNotNull();
        assertThat(deleteContactResponse.getMessage()).isEqualTo("DeletedContact.SUCCESSFULLY");

        Optional<Contacts> deletedContact = contactRepository.findByPhoneNumber("54321");
        assertThat(deletedContact).isEmpty();
    }






}
