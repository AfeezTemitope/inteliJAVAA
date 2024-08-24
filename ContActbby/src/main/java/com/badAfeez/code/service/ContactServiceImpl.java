package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateContactRequest;
import com.badAfeez.code.DtoBBY.request.DeleteContactRequest;
import com.badAfeez.code.DtoBBY.request.UpdateContactRequest;
import com.badAfeez.code.DtoBBY.response.DeleteContactResponse;

import com.badAfeez.code.DtoBBY.response.UpdateContactResponse;
import com.badAfeez.code.Exception.ContactNotFound;
import com.badAfeez.code.Exception.InvalidPhoneNumber;
import com.badAfeez.code.Exception.UserNotFound;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    private User LogIn = null;


    @Override
    public List<Contacts> getAllContacts(String phoneNumber) {
        if (LogIn == null) throw new RuntimeException("User must be logged in to access contacts");
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(()-> new UserNotFound());
        if (!user.equals(LogIn)) throw new RuntimeException("Unauthorized access");
        List<Contacts> contacts = contactRepository.findByUser(user);
        if (contacts.isEmpty()) return  Collections.emptyList();
        return contacts;
    }

    @Override
    public Contacts addContact(CreateContactRequest createContactRequest) {
        if (LogIn == null) throw new RuntimeException("User must be logged in to add contacts");
        Contacts newContact = new Contacts();
        newContact.setName(createContactRequest.getName());
        validatePhoneNumber(createContactRequest.getPhoneNumber());
        newContact.setPhoneNumber(createContactRequest.getPhoneNumber());
        newContact.setUser(LogIn);
        contactRepository.save(newContact);

        List<Contacts> userContacts = LogIn.getContacts();
        if (userContacts == null) userContacts = new ArrayList<>();

        userContacts.add(newContact);
        LogIn.setContacts(userContacts);
        userRepository.save(LogIn);
        return newContact;
    }

    @Override
    public void setLoggedInUser(User user) {
        this.LogIn = user;
    }

    @Override
    public User getLoggedInUser() {
        return this.LogIn;
    }

    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest) {
        if (LogIn == null) throw new RuntimeException("User must be logged in to update contacts");
        validatePhoneNumber(updateContactRequest.getPhoneNumber());
        Contacts contacts = contactRepository.findByPhoneNumber(updateContactRequest.getPhoneNumber()).orElseThrow(()-> new ContactNotFound("contact does not exist"));
        contacts.setPhoneNumber(updateContactRequest.getNewPhoneNumber());
        contacts.setName(updateContactRequest.getNewName());
        contactRepository.save(contacts);
        UpdateContactResponse updateContactResponse = new UpdateContactResponse();
        updateContactResponse.setMessage("updated.SuCCESSFULLY");
        return updateContactResponse;
    }

    private void validatePhoneNumber(String phoneNumber){
        if (phoneNumber.length() != 11) throw new InvalidPhoneNumber();
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest) {
        if (LogIn == null) throw new RuntimeException("User must be logged in to delete contacts");
        validatePhoneNumber(deleteContactRequest.getPhoneNumber());
        Contacts contacts = contactRepository.findByPhoneNumber(deleteContactRequest.getPhoneNumber()).orElseThrow(()-> new ContactNotFound("contact does not exist"));
        contactRepository.delete(contacts);
        DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
        deleteContactResponse.setMessage("deleted.SuCCESSFULLY");
        return deleteContactResponse;
    }
}

