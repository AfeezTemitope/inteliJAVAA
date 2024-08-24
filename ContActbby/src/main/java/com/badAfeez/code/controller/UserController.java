package com.badAfeez.code.controller;

import com.badAfeez.code.DtoBBY.request.CreateUserRequest;
import com.badAfeez.code.DtoBBY.request.LoginUserRequest;
import com.badAfeez.code.DtoBBY.response.CreateUserResponse;
import com.badAfeez.code.DtoBBY.response.LoginResponse;
import com.badAfeez.code.DtoBBY.response.UserWithContactsResponse;
import com.badAfeez.code.Exception.UserNotFound;
import com.badAfeez.code.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        if ("User already exists".equals(response.getMessage())) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        LoginResponse response = userService.isUserLoggedIn(request);
        if ("invalid.CREDENTIALS".equals(response.getMessage())) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{phoneNumber}/contacts")
    public ResponseEntity<?> getUserWithContacts(@PathVariable String phoneNumber) {
        try {
            UserWithContactsResponse response = userService.getUserWithContacts(phoneNumber);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
