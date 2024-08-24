package com.badAfeez.code.service;

import com.badAfeez.code.data.models.Users;
import com.badAfeez.code.data.repository.UsersRepository;
import com.badAfeez.code.dtObby.request.CreateUserRequest;
import com.badAfeez.code.dtObby.request.LoginUserRequest;
import com.badAfeez.code.dtObby.response.CreateUserResponse;
import com.badAfeez.code.dtObby.response.LoginUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesIml implements UserServices{

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Long countNumberOfUsers() {
        return usersRepository.count();
    }

    @Override
    public CreateUserResponse createNewUser(CreateUserRequest request) {
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        usersRepository.save(user);

        CreateUserResponse response = new CreateUserResponse();
        response.setMessage("Successfully created user");
        return response;
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginRequest) {
        Users user = usersRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            LoginUserResponse response = new LoginUserResponse();
            response.setMessage("User not found");
            response.setSuccess(false);
            return response;
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            LoginUserResponse response = new LoginUserResponse();
            response.setMessage("Invalid password");
            response.setSuccess(false);
            return response;
        }

        LoginUserResponse response = new LoginUserResponse();
        response.setUserId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setMessage("Login successful");
        response.setSuccess(true);

        return response;
    }



    }

