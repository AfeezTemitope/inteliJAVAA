package com.badAfeez.artSalesMgt.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArtworkRequest {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private int quantity;
}
