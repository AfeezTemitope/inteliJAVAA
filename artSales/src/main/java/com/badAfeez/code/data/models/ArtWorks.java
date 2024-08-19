package com.badAfeez.code.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ArtWorks {
    @Id
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    @DBRef
    private Artist artist;
    private int availableQuantity;

    @Override
    public String toString() {
        return "ArtWorks{" +
                "id=" + id +
                ", title=" + title + '\'' +
                ", artist=" + artist + '\'' +
                ", description=" + description '\'' +
               ", imageUrl=" + imageUrl + '\'' +
                '}';


    }
