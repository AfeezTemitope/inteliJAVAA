package com.badAfeez.code.DtoBBY.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateContactRequest {

    private String userId;
    private String name;
    private String phoneNumber;
    private String email;



}
