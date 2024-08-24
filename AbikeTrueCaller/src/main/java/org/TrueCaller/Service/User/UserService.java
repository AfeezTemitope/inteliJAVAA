package org.TrueCaller.Service.User;

import org.TrueCaller.data.models.Contact;
import org.TrueCaller.data.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Contact> getAllContact();
    User addContact(String userName, Contact contact);
    User updateContact(String userName, Contact contact);
    User deleteContact(String userName, String id);
    Optional<Contact> getContactById(String id);
    User getUserById(String id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User addUser(String userName, User user);
    User verifyUser(String userName, String password);

}
