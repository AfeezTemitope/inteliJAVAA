package com.example.contact.Services;

import com.example.contact.DTos.request.CreateUserRequest;
import com.example.contact.DTos.request.LoginUserRequest;
import com.example.contact.DTos.response.CreateUserResponse;
import com.example.contact.DTos.response.LoginUserResponse;

public interface UserServices {
    CreateUserResponse registerUser(CreateUserRequest createUserRequest);

    LoginUserResponse login(LoginUserRequest loginUserRequest);
}
