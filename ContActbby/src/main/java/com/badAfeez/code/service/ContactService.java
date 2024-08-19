package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateContactRequest;
import com.badAfeez.code.DtoBBY.request.DeleteContactRequest;
import com.badAfeez.code.DtoBBY.request.UpdateContactRequest;
import com.badAfeez.code.DtoBBY.response.CreateContactResponse;
import com.badAfeez.code.DtoBBY.response.DeleteContactResponse;
import com.badAfeez.code.DtoBBY.response.UpdateContactResponse;
import com.badAfeez.code.data.models.Contacts;

import java.util.List;

public interface ContactService {

    CreateContactResponse addContact(CreateContactRequest createContactRequest);
    DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest);
    UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest);
    List<Contacts> getUserContacts(String userId);

}
