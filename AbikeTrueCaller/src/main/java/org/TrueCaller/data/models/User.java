package org.TrueCaller.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Data
public class User {

    private String id;
    private String name;
    private List<Contact> contacts;
    private String password;
    private String email;
    private String userName;
}
