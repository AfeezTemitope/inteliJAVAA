package com.badAfeez.code.dtoBby.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePurchaseRequest {

    private String customerId;
    private String artworkId;
    private int quantity;
    private double totalPrice;
    private String status;
    private LocalDateTime orderDate;
}
