package com.badAfeez.code.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Artist {
    @Id
    private String id;
    private String name;
    private String age;
    private String gender;
    private String phoneNumber;
    private String userName;
    @DBRef
    private List< ArtWorks> artWorks = new ArrayList<>();
}
