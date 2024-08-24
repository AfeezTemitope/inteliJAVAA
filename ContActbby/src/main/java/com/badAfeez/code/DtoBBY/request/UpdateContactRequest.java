package com.badAfeez.code.DtoBBY.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UpdateContactRequest {

    private String phoneNumber;
    private String newPhoneNumber;
    private String newName;
}
