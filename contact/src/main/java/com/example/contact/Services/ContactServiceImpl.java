package com.example.contact.Services;

import com.example.contact.DTos.request.AddContactRequest;
import com.example.contact.DTos.response.AddContactResponse;
import com.example.contact.Exception.UserNotFoundException;
import com.example.contact.data.models.Contacts;
import com.example.contact.data.models.User;
import com.example.contact.data.repository.ContactRepository;
import com.example.contact.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.contact.Exception.UserNotLoggedIn;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;


    @Override
    public AddContactResponse addContact(AddContactRequest addContactRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  (authentication != null) ? authentication.getName() : "null";
        if (username == null) throw new UserNotLoggedIn("user is not logged in");
        User user = userRepository.findByUsername(username.trim().toLowerCase());
        if (user == null) throw new UserNotFoundException("user not found");
        if (addContactRequest.getContactName() == null || addContactRequest.getContactName().isEmpty() ||
                addContactRequest.getContactNumber() == null || addContactRequest.getContactNumber().isEmpty()) {
            throw new IllegalArgumentException("Contact name and number cannot be empty");
        }
        try {
            Contacts contact = new Contacts();
            contact.setUserid(String.valueOf(user));
            contact.setContactName(addContactRequest.getContactName());
            contact.setContactPhone(addContactRequest.getContactNumber());
            contactRepository.save(contact);

            AddContactResponse addContactResponse = new AddContactResponse();
            addContactResponse.setMessage("contact.added.SUCCESSFULLY");
            return addContactResponse;
        }catch (Exception e){
            throw new IllegalArgumentException("Contact not added");
    }
}
}
