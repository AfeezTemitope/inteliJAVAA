package com.badAfeez.code.DtoBBY.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UpdateContactRequest {
    @Id
    private String  id;
    private String  userId;
    private String oldPhoneNumber;
    private String newPhoneNumber;
    private String newName;
}
