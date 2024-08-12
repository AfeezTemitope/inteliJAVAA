package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateContactRequest;
import com.badAfeez.code.DtoBBY.request.DeleteContactRequest;
import com.badAfeez.code.DtoBBY.request.UpdateContactRequest;
import com.badAfeez.code.DtoBBY.response.CreateContactResponse;
import com.badAfeez.code.DtoBBY.response.DeleteContactResponse;
import com.badAfeez.code.DtoBBY.response.GetContactsResponse;
import com.badAfeez.code.DtoBBY.response.UpdateContactResponse;

public interface ContactService {

    CreateContactResponse addContact(CreateContactRequest createContactRequest);
    DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest);
    UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest);

}
