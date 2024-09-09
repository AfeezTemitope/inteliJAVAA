package com.example.contact.DTos.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private Long id;
}
