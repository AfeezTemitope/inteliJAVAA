package com.badAfeez.code.dtoBby.request;

import com.badAfeez.code.data.models.UserRole;
import lombok.Data;

@Data
public class RegisterCustomerRequest {
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private String password;
    private UserRole userRole;

}
