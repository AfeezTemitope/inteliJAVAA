package com.badAfeez.artSalesMgt.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Artworks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private int quantity;
    //private String artworksTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}
