package com.example.contact.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    private String contactName;
    private String contactPhone;
    private String userid;
}
