package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateContactRequest;
import com.badAfeez.code.DtoBBY.request.DeleteContactRequest;
import com.badAfeez.code.DtoBBY.request.UpdateContactRequest;
import com.badAfeez.code.DtoBBY.response.DeleteContactResponse;
import com.badAfeez.code.DtoBBY.response.UpdateContactResponse;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.models.User;

import java.util.List;

public interface ContactService {

    Contacts addContact(CreateContactRequest createContactRequest);
    DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest);
    List<Contacts> getAllContacts(String phoneNumber);
    void setLoggedInUser(User user);
    User getLoggedInUser();

    UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest);
}
