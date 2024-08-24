package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.*;
import com.badAfeez.code.DtoBBY.response.*;
import com.badAfeez.code.Exception.UserNotFound;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    private User user;
    private User secondUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        contactRepository.deleteAll();

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("badAfeez");
        createUserRequest.setPassword("123456");
        createUserRequest.setEmail("badAfeez@gmail.com");
        createUserRequest.setPhoneNumber("12345678901");
        CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
        assertNotNull(createUserResponse);

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setPhoneNumber(createUserRequest.getPhoneNumber());
        loginUserRequest.setPassword(createUserRequest.getPassword());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("login.SUCCESSFUL");
        user = userRepository.findByPhoneNumber("12345678901").orElseThrow(() -> new UserNotFound());
        contactService.setLoggedInUser(user);

    }
    @Test
    void testThatUserCanAddContact(){
        User user = userRepository.findByPhoneNumber("12345678901").orElseThrow(() -> new UserNotFound());
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("myChi");
        createContactRequest.setPhoneNumber("09876543211");

        Contacts createContactResponse = contactService.addContact(createContactRequest);
        assertNotNull(createContactResponse);

        List<Contacts> contacts = contactRepository.findByUser(user);
        assertFalse(contacts.isEmpty());
        assertEquals(1, contacts.size());
    }
    @Test
    void testThatUserCanAdd2ContactAndSeeAllContacts(){
        User user = userRepository.findByPhoneNumber("12345678901").orElseThrow(() -> new RuntimeException("User not found"));
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("myChi");
        createContactRequest.setPhoneNumber("09876543211");

        Contacts createContactResponse = contactService.addContact(createContactRequest);
        assertNotNull(createContactResponse);

        CreateContactRequest createContactRequest2 = new CreateContactRequest();
        createContactRequest2.setName("myChi2");
        createContactRequest2.setPhoneNumber("09876543211");
        Contacts createContactResponse2 = contactService.addContact(createContactRequest2);
        assertNotNull(createContactResponse2);

        List<Contacts> contacts = contactRepository.findByUser(user);
        assertFalse(contacts.isEmpty());
        assertEquals(2, contacts.size());

        UserWithContactsResponse userWithContactsResponse = userService.getUserWithContacts("12345678901");
        assertNotNull(userWithContactsResponse);

        List<Contacts> contactsFromResponse = userWithContactsResponse.getContacts();
        assertNotNull(contactsFromResponse);
        assertEquals(2, contactsFromResponse.size());

        System.out.println("Contacts from response:");
        for (Contacts contact : contactsFromResponse) {
            System.out.println("Name: " + contact.getName() + ", Phone Number: " + contact.getPhoneNumber());
        }
    }
    @Test
    void testThatUserCanUpdateContactInformation() {
        User user = userRepository.findByPhoneNumber("12345678901").orElseThrow(() -> new UserNotFound());
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName("myChi");
        createContactRequest.setPhoneNumber("09876543211");
        Contacts createContactResponse = contactService.addContact(createContactRequest);
        assertNotNull(createContactResponse);

        UpdateContactRequest updateContactRequest = new UpdateContactRequest();
        updateContactRequest.setPhoneNumber(createContactRequest.getPhoneNumber());
        updateContactRequest.setNewPhoneNumber("12345678890");
        updateContactRequest.setNewName("myChi2");

        UpdateContactResponse updateContact = contactService.updateContact(updateContactRequest);
        assertNotNull(updateContact);
    }
    @Test
    void testThatContactCanBeDeleted() {
        CreateContactRequest createContactRequest1 = new CreateContactRequest();
        createContactRequest1.setName("Contact1");
        createContactRequest1.setPhoneNumber("11111111111");
        Contacts contact1 = contactService.addContact(createContactRequest1);
        assertNotNull(contact1);

        CreateContactRequest createContactRequest2 = new CreateContactRequest();
        createContactRequest2.setName("Contact2");
        createContactRequest2.setPhoneNumber("22222222222");
        Contacts contact2 = contactService.addContact(createContactRequest2);
        assertNotNull(contact2);

        List<Contacts> contactsBeforeDeletion = contactRepository.findByUser(user);
        assertEquals(2, contactsBeforeDeletion.size());

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setPhoneNumber("11111111111");

        DeleteContactResponse deleteContactResponse = contactService.deleteContact(deleteContactRequest);
        assertNotNull(deleteContactResponse);
        assertEquals("deleted.SuCCESSFULLY", deleteContactResponse.getMessage());

        List<Contacts> contactsAfterDeletion = contactRepository.findByUser(user);
        assertEquals(1, contactsAfterDeletion.size());

        System.out.println("Remaining Contacts:");
        for (Contacts contact : contactsAfterDeletion) {
            System.out.println("Name: " + contact.getName() + ", Phone Number: " + contact.getPhoneNumber());
        }
    }
    }
