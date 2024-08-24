package com.badAfeez.code.service;

import com.badAfeez.code.dtObby.request.CreateUserRequest;
import com.badAfeez.code.dtObby.request.LoginUserRequest;
import com.badAfeez.code.dtObby.response.CreateUserResponse;
import com.badAfeez.code.dtObby.response.LoginUserResponse;

public interface UserServices {
    Long countNumberOfUsers();

    CreateUserResponse createNewUser(CreateUserRequest request);

    LoginUserResponse loginUser(LoginUserRequest loginRequest);
}
