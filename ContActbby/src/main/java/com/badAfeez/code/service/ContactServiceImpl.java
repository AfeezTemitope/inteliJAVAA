package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateContactRequest;
import com.badAfeez.code.DtoBBY.request.DeleteContactRequest;
import com.badAfeez.code.DtoBBY.request.UpdateContactRequest;
import com.badAfeez.code.DtoBBY.response.CreateContactResponse;
import com.badAfeez.code.DtoBBY.response.DeleteContactResponse;

import com.badAfeez.code.DtoBBY.response.UpdateContactResponse;
import com.badAfeez.code.Exception.ContactNotFound;
import com.badAfeez.code.Exception.UserNotFound;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateContactResponse addContact(CreateContactRequest createContactRequest) {
        userRepository.findById(createContactRequest.getUserId()).orElseThrow(()-> new UserNotFound());
        boolean isContactExisting = contactRepository.existsByPhoneNumberAndId(createContactRequest.getPhoneNumber(),createContactRequest.getUserId());
        if (isContactExisting) {
            throw new IllegalArgumentException("Contact with this phone number already exists");
        }
        Contacts newContact = new Contacts();
        newContact.setName(createContactRequest.getName());
        newContact.setPhoneNumber(createContactRequest.getPhoneNumber());
        newContact.setEmail(createContactRequest.getEmail());
        newContact.setUserId(createContactRequest.getUserId());
        contactRepository.save(newContact);

        CreateContactResponse createContactResponse = new CreateContactResponse();
        createContactResponse.setMessage("added contact successfully");
        return createContactResponse;
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest) {
        userRepository.findById(deleteContactRequest.getUserId()).orElseThrow(()-> new ContactNotFound("contact not found"));
        Optional<Contacts> contacts = contactRepository.findByPhoneNumber(deleteContactRequest.getPhoneNumber());
        DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
        if (contacts.isPresent()) {
            contactRepository.delete(contacts.get());
            deleteContactResponse.setMessage("DeletedContact.SUCCESSFULLY");
        } else {
            deleteContactResponse.setMessage("DeletedContact.FAILED");
        }
        return deleteContactResponse;
    }

    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest) {
        User user = userRepository.findById(updateContactRequest.getUserId()).orElseThrow(()->new UserNotFound());
        Optional<Contacts> existingContacts = contactRepository.findByPhoneNumber(updateContactRequest.getOldPhoneNumber());

        if (existingContacts.isPresent()) {
            Contacts contacts = existingContacts.get();
            contacts.setName(updateContactRequest.getNewName());
            if (!updateContactRequest.getOldPhoneNumber().equals(updateContactRequest.getNewPhoneNumber())){
                Optional<Contacts> contactWithNewNumber = contactRepository.findByPhoneNumber(updateContactRequest.getNewPhoneNumber());
                if (contactWithNewNumber.isPresent()) {
                UpdateContactResponse updateContactResponse = new UpdateContactResponse();
                updateContactResponse.setMessage("Number already in use");
                return updateContactResponse;
                }
                contacts.setPhoneNumber(updateContactRequest.getNewPhoneNumber());
            }
            contactRepository.save(contacts);
            UpdateContactResponse updateContactResponse = new UpdateContactResponse();
            updateContactResponse.setMessage("updatedContact.SUCCESSFULLY");
            return updateContactResponse;
            } else {
            UpdateContactResponse updateContactResponse = new UpdateContactResponse();
            updateContactResponse.setMessage("updatedContact.FAILED");
            return updateContactResponse;
        }
//            contacts.setPhoneNumber(updateContactRequest.getNewPhoneNumber());
//            contactRepository.save(contacts);
//            UpdateContactResponse updateContactResponse = new UpdateContactResponse();
//            updateContactResponse.setMessage("updatedContact.SUCCESSFULLY");
//            return updateContactResponse;
//        } else {
//            UpdateContactResponse updateContactResponse = new UpdateContactResponse();
//            updateContactResponse.setMessage("updatedContact.FAILED");
//            return updateContactResponse;
//        }
    }

    @Override
    public List<Contacts> getUserContacts(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFound());
        return contactRepository.findByUserId(userId);
    }
    private User validateUser(String userId){
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFound());
    }

}

