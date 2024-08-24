package com.badAfeez.code.DtoBBY.request;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String phoneNumber;
    private String password;
}
