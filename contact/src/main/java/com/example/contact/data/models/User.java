package com.example.contact.data.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
