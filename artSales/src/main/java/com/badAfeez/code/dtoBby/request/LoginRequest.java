package com.badAfeez.code.dtoBby.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String customerEmail;
    private String password;
}
