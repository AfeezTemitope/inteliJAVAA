package com.badAfeez.code.DtoBBY.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateUserRequest {
    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
}
