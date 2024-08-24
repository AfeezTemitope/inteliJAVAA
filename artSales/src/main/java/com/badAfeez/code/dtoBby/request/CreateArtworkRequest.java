package com.badAfeez.code.dtoBby.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateArtworkRequest {

    private String artWorkId;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private String artistId;
    private int availableQuantity;

}
