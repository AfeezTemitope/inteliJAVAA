package com.badAfeez.code.dtObby.response;

import lombok.Data;

@Data
public class LoginUserResponse {
    private String message;
    private String UserId;
    private String name;
    private String email;
    private boolean success;
}
