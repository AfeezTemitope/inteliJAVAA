package com.badAfeez.code.dtObby.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
