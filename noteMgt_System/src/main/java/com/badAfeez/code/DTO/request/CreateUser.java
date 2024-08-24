package com.badAfeez.code.DTO.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateUser {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
