package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateUserRequest;
import com.badAfeez.code.DtoBBY.request.LoginUserRequest;
import com.badAfeez.code.DtoBBY.response.CreateUserResponse;
import com.badAfeez.code.DtoBBY.response.LoginResponse;
import com.badAfeez.code.Exception.UserNotFound;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.data.repository.ContactRepository;
import com.badAfeez.code.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private  UserRepository userRepository;


    @Override
    public Long countNumberOfUser() {
        return userRepository.count();
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request){
//        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
//        if(existingUser.isPresent()){
//            CreateUserResponse response = new CreateUserResponse();
//            response.setMessage("User already existing");
//            return response;
//        }
        Optional<User> existingUserByPhoneNumber = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (existingUserByPhoneNumber.isPresent()) {
            CreateUserResponse response = new CreateUserResponse();
            response.setMessage("Phone number already registered");
            return response;
        }
        User user = new User();
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        //user.setId(request.getId());
        userRepository.save(user);

        CreateUserResponse response = new CreateUserResponse();
        response.setMessage("created.SUCCESSFULLY");
        response.setId(user.getId());
        return response;
    }

    @Override
    public LoginResponse isUserLoggedIn(LoginUserRequest loginUserRequest){
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginUserRequest.getPhoneNumber());
        User user = optionalUser.orElseThrow(()-> new UserNotFound());
        LoginResponse response = new LoginResponse();
        if(loginUserRequest.getPassword().equals(user.getPassword())){
            response.setMessage("login.SUCCESSFUL");

        } else {
            response.setMessage("invalid.CREDENTIALS");
        } return response;
    }
//    @Override
//    public LoginResponse isUserLoggedIn(LoginUserRequest loginUserRequest) {
//        User user = userRepository.findByPhoneNumber(loginUserRequest.getPhoneNumber())
//                .orElseThrow(UserNotFound::new);
//
//        boolean matches = passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword());
//        return new LoginResponse matches ? "login>SUCCESSFUL" : "invalid.CREDENTIALS";
//
//    }

    }