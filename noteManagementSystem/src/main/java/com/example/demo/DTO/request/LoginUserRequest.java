package com.example.demo.DTO.request;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String username;
    private String password;
}
