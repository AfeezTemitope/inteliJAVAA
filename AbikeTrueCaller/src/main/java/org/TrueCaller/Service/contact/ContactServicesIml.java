package org.TrueCaller.Service.contact;

import org.TrueCaller.Exception.ContactNotFound;
import org.TrueCaller.data.models.Contact;
import org.TrueCaller.data.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServicesIml implements ContactServices {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        Contact newContact = new Contact();
        newContact.setId(contact.getId());
//        newContact.setName(contact.getName());
//        newContact.setPhoneNumber(contact.getPhoneNumber());
        return contactRepository.save(newContact);
    }

    @Override
    public void deleteContact(Contact contact) {
        Contact isDeleted = contactRepository.findById(contact.getId()).orElseThrow(()-> new ContactNotFound("Contact Not Found"));
        contactRepository.delete(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        if (contactRepository.existsById(contact.getId())) contactRepository.save(contact);
    }
}
