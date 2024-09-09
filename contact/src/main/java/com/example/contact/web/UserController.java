package com.example.contact.web;

import com.example.contact.DTos.request.CreateUserRequest;
import com.example.contact.DTos.response.CreateUserResponse;
import com.example.contact.Services.UserServiceImpl;
import com.example.contact.Services.UserServices;
import com.example.contact.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userService;

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest createUserRequest ){
        CreateUserResponse createUserResponse = userService.registerUser(createUserRequest);
        if ("user already exists".equalsIgnoreCase(createUserResponse.getMessage())){
            return ResponseEntity.ok(createUserResponse);
        }
        return ResponseEntity.ok(createUserResponse);
    }
}
