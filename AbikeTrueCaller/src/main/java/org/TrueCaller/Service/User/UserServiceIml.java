package org.TrueCaller.Service.User;

import org.TrueCaller.data.models.Contact;
import org.TrueCaller.data.models.User;
import org.TrueCaller.data.repository.ContactRepository;
import org.TrueCaller.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceIml implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContact() {
        return List.of();
    }

    @Override
    public User addContact(String userName, Contact contact) {
        return null;
    }

    @Override
    public User updateContact(String userName, Contact contact) {
        return null;
    }

    @Override
    public User deleteContact(String userName, String id) {
        return null;
    }

    @Override
    public Optional<Contact> getContactById(String id) {
        return Optional.empty();
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User addUser(String userName, User user) {
        return null;
    }

    @Override
    public User verifyUser(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password) ) return user;
        return null;
    }
}
