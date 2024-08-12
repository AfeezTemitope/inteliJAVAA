package com.badAfeez.code.controller;

import com.badAfeez.code.DtoBBY.request.CreateUserRequest;
import com.badAfeez.code.DtoBBY.request.LoginUserRequest;
import com.badAfeez.code.DtoBBY.response.CreateUserResponse;
import com.badAfeez.code.DtoBBY.response.LoginResponse;
import com.badAfeez.code.data.models.User;
import com.badAfeez.code.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            CreateUserResponse createUserResponse = userServices.createUser(createUserRequest);
            return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
        } catch (Exception e){
            CreateUserResponse createUserResponse = new CreateUserResponse();
            createUserResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(createUserResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?>loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            LoginResponse loginResponse = userServices.isUserLoggedIn(loginUserRequest);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }catch (Exception e){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
