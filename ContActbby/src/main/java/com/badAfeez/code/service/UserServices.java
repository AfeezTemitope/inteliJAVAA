package com.badAfeez.code.service;

import com.badAfeez.code.DtoBBY.request.CreateUserRequest;
import com.badAfeez.code.DtoBBY.request.LoginUserRequest;
import com.badAfeez.code.DtoBBY.response.CreateUserResponse;
import com.badAfeez.code.DtoBBY.response.LoginResponse;


public interface UserServices {
    Long countNumberOfUser();
    CreateUserResponse createUser(CreateUserRequest request);

    LoginResponse isUserLoggedIn(LoginUserRequest loginRequest);
}
