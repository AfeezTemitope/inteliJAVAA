package com.example.demo.DTO.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String id;
}
