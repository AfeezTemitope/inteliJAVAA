package com.example.contact.Services;

import com.example.contact.DTos.request.AddContactRequest;
import com.example.contact.DTos.response.AddContactResponse;


public interface ContactService {
    AddContactResponse addContact(AddContactRequest addContactRequest);
}
