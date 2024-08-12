package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateUserRequest;
import com.badAfeez.code.DtoBBY.request.LoginUserRequest;
import com.badAfeez.code.DtoBBY.response.CreateUserResponse;
import com.badAfeez.code.DtoBBY.response.LoginResponse;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Long countNumberOfUser() {
        return userRepository.count();
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        CreateUserResponse response = new CreateUserResponse();
        response.setMessage("created.OK");
        return response;
    }

    @Override
    public LoginResponse isUserLoggedIn(LoginUserRequest loginRequest) {
        User user = new User();
        user.setPhoneNumber(loginRequest.getPhoneNumber());
        user.setPassword(loginRequest.getPassword());

        LoginResponse response = new LoginResponse();
        response.setMessage("login.OK");
        return response;
    }


}
