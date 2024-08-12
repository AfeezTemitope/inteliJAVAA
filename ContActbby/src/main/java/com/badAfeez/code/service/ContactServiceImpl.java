package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateContactRequest;
import com.badAfeez.code.DtoBBY.request.DeleteContactRequest;
import com.badAfeez.code.DtoBBY.request.UpdateContactRequest;
import com.badAfeez.code.DtoBBY.response.CreateContactResponse;
import com.badAfeez.code.DtoBBY.response.DeleteContactResponse;
import com.badAfeez.code.DtoBBY.response.GetContactsResponse;
import com.badAfeez.code.DtoBBY.response.UpdateContactResponse;
import com.badAfeez.code.Exception.ContactNotFound;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public CreateContactResponse addContact(CreateContactRequest createContactRequest) {
        if (contactRepository.existsByPhoneNumber(createContactRequest.getPhoneNumber())) {
            throw new IllegalArgumentException("Contact with this phone number already exists");
        }
        Contacts newContact = new Contacts();
        newContact.setName(createContactRequest.getName());
        newContact.setPhoneNumber(createContactRequest.getPhoneNumber());
        contactRepository.save(newContact);

        CreateContactResponse createContactResponse = new CreateContactResponse();
        createContactResponse.setMessage("added contact successfully");
        return createContactResponse;
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest) {
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
        Optional<Contacts> existingContacts = contactRepository.findByPhoneNumber(updateContactRequest.getOldPhoneNumber());

        if (existingContacts.isPresent()) {
            Contacts contacts = existingContacts.get();
            contacts.setName(updateContactRequest.getNewName());

            contacts.setPhoneNumber(updateContactRequest.getNewPhoneNumber());

            contactRepository.save(contacts);

            UpdateContactResponse updateContactResponse = new UpdateContactResponse();
            updateContactResponse.setMessage("updatedContact.SUCCESSFULLY");
            return updateContactResponse;
        } else {
            UpdateContactResponse updateContactResponse = new UpdateContactResponse();
            updateContactResponse.setMessage("updatedContact.FAILED");
            return updateContactResponse;
        }
    }



}

