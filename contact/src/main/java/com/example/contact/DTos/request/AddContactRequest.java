package com.example.contact.DTos.request;

import lombok.Data;

@Data
public class AddContactRequest {
    private String contactName;
    private String contactNumber;
    private String userId;
}
