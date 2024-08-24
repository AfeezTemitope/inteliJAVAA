package com.badAfeez.code.dtoBby.response;

import com.badAfeez.code.data.models.UserRole;
import lombok.Data;

@Data
public class LoginResponse {
    private String customerId;
    private String message;
    private UserRole role;
}
