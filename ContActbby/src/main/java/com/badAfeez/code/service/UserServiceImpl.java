package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateUserRequest;
import com.badAfeez.code.DtoBBY.request.LoginUserRequest;
import com.badAfeez.code.DtoBBY.response.CreateUserResponse;
import com.badAfeez.code.DtoBBY.response.LoginResponse;
import com.badAfeez.code.DtoBBY.response.UserWithContactsResponse;
import com.badAfeez.code.Exception.UserFound;
import com.badAfeez.code.Exception.UserNotFound;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    @Override
    public Long countNumberOfUser() {
        return userRepository.count();
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        userRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(() -> new UserFound());
        CreateUserResponse response = new CreateUserResponse();
        User newUser = new User();
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail().toLowerCase());
        newUser.setName(request.getName());
        User latest = userRepository.save(newUser);

        response.setMessage("User created successfully");
        response.setId(latest.getId());
        return response;
    }

    @Override
    public LoginResponse isUserLoggedIn(LoginUserRequest loginUserRequest) {
        User user = userRepository.findByPhoneNumber(loginUserRequest.getPhoneNumber()).orElseThrow(() -> new UserNotFound());
        LoginResponse response = new LoginResponse();
        boolean verifyLogin = passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword());
        String message = verifyLogin ? "login.SUCCESSFUL" : "invalid.CREDENTIALS";
        response.setMessage(message);
        if (verifyLogin) contactService.setLoggedInUser(user);
        return response;
    }

    @Override
    public UserWithContactsResponse getUserWithContacts(String phoneNumber) {
        if (contactService.getLoggedInUser() == null) throw new UserNotFound();
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFound());

        List<Contacts> contacts = contactRepository.findByUser(user);

        UserWithContactsResponse response = new UserWithContactsResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setContacts(contacts);

        return response;
    }
    private User getLoggedInUser() {
       return contactService.getLoggedInUser();
    }
}