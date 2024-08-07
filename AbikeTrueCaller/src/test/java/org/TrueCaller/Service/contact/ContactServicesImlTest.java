package org.TrueCaller.Service.contact;

import org.TrueCaller.Exception.ContactNotFound;
import org.TrueCaller.data.models.Contact;
import org.TrueCaller.data.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServicesImlTest {
    @Autowired
    private ContactServices contactServices;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    void testThatRepositoryCanAddContact() {
    Contact contact = new Contact();
    contact.setId("qwe");
    contact.setName("Bad boy afeez");
    contact.setPhoneNumber("09014465194");
    contactServices.addContact(contact);
    assertEquals(1, contactRepository.count());
    }

    @Test
    void testThatRepositoryCanAddTwoContacts() {
        Contact contact = new Contact();
        contact.setId("qwe");
        contact.setName("Bad boy afeez");
        contact.setPhoneNumber("09014465194");
        contactServices.addContact(contact);
        //assertEquals(1, contactRepository.count());
        Contact contact2 = new Contact();
        contact2.setId("qwe2");
        contact2.setName("Bad boy afeez");
        contact2.setPhoneNumber("09014465194");
        contactServices.addContact(contact2);
        assertEquals(2, contactRepository.count());
    }
    @Test
    void testThatContactCanBeDeleted() {
    Contact contact = new Contact();
    contact.setId("qwe");
    contact.setName("Bad boy afeez");
    contact.setPhoneNumber("09014465194");
    contactServices.addContact(contact);
    assertEquals(1, contactRepository.count());
    Contact isDeletedContact = contactRepository.findById("qwe").orElseThrow(()->new ContactNotFound("Contact not found"));
    contactRepository.delete(isDeletedContact);
    assertEquals(0, contactRepository.count());
    }

    @Test
    void updateContact() {
        Contact contact = new Contact();
        contact.setId("qwe");
        contact.setName("Bad boy afeez");
        contact.setPhoneNumber("09014465194");
        contactServices.addContact(contact);
        assertEquals(1, contactRepository.count());
        Contact updatedContact = contactRepository.findById("qwe").orElseThrow(()->new ContactNotFound("Contact not found"));
        updatedContact.setName("chibuzor!!!");
        contactServices.updateContact(updatedContact);

        Contact retrievedContact = contactRepository.findById("qwe").orElseThrow(()->new ContactNotFound("Contact not found"));
        assertEquals("chibuzor!!!", retrievedContact.getName());

    }

    @AfterEach
    void cleanup() {
        contactRepository.deleteAll();
    }
}