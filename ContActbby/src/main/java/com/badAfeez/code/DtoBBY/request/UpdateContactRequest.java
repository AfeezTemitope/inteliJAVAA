package com.badAfeez.code.DtoBBY.request;

import lombok.Data;

@Data
public class UpdateContactRequest {
    private String oldPhoneNumber;
    private String newPhoneNumber;
    private String newName;
}
