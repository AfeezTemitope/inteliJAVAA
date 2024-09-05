package com.example.demo.Services;

import com.example.demo.DTO.request.CreateUserRequest;
import com.example.demo.DTO.response.CreateUserResponse;
import com.example.demo.data.models.User;
import org.springframework.stereotype.Repository;

public interface UserService {
    long countNumberOfUser();

    CreateUserResponse createUser(CreateUserRequest createUser);

    User login(String username, String password);
}
