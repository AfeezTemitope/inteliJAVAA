package com.badAfeez.code.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private String artworkId;
    private int quantity;
    private double totalPrice;
    private String status;
    private LocalDateTime orderDate;

}
