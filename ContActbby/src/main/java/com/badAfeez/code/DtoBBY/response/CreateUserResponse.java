package com.badAfeez.code.DtoBBY.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateUserResponse {
    private String message;
    @Id
    private String id;
}
