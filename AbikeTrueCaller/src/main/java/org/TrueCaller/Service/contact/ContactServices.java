package org.TrueCaller.Service.contact;

import org.TrueCaller.data.models.Contact;

public interface ContactServices {
    Contact addContact(Contact contact);
    void deleteContact(Contact contact);
    void updateContact(Contact contact);

}
