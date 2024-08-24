package com.badAfeez.code.DtoBBY.response;

import com.badAfeez.code.data.models.Contacts;
import lombok.Data;

import java.util.List;
@Data
public class UserWithContactsResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private List<Contacts> contacts;
}
