package com.badAfeez.code.data.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Users {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    List<Note> notes;
}

