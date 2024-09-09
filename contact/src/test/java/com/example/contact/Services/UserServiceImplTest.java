package com.example.contact.Services;

import com.example.contact.DTos.request.CreateUserRequest;
import com.example.contact.DTos.request.LoginUserRequest;
import com.example.contact.DTos.response.CreateUserResponse;
import com.example.contact.DTos.response.LoginUserResponse;
import com.example.contact.Exception.InvalidCredentialsException;
import com.example.contact.Exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServices userService;

    @Test
    void testThatUserCanRegister() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("badAfeez2");
        createUserRequest.setPassword("admin123");
        CreateUserResponse response = userService.registerUser(createUserRequest);
        response.setMessage("registered.SUCCESSFULLY");
        response.setUserId(response.getUserId());
        System.out.println(response.getUserId());
    }
    @Test
    void testThatUserCannotRegisterTwice() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("badAfeez");
        createUserRequest.setPassword("admin123");
        assertThrows(UserNotFoundException.class, () -> userService.registerUser(createUserRequest));
    }
    @Test
    void testThatUserCanLogin() {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("badAfeez");
        loginUserRequest.setPassword("admin123");
        LoginUserResponse response = userService.login(loginUserRequest);
        response.setMessage("login.SUCCESSFULLY");

    }
    @Test
    void testThatUserCannotLoginWithWrongPassword() {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("badAfeez");
        loginUserRequest.setPassword("admin1235");
        assertThrows(InvalidCredentialsException.class, () -> userService.login(loginUserRequest));
    }
    @Test
    void testThatUserCannotLoginIfNotRegistered(){
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("wrong");
        loginUserRequest.setPassword("badAdmin123");
        assertThrows(UserNotFoundException.class, () -> userService.login(loginUserRequest));
    }
}