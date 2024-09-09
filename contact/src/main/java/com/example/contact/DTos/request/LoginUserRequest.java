package com.example.contact.DTos.request;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String username;
    private String password;
}
