package com.badAfeez.code.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Contacts {
    @Id
    private String Id;
    private String name;
    private String phoneNumber;

    @DBRef
    private String userId;
}
