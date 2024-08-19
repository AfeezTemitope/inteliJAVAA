package com.badAfeez.code.services.Order;

import com.badAfeez.code.Exception.ArtWorkNotFound;
import com.badAfeez.code.Exception.QuantityExceedAvailable;
import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.Order;
import com.badAfeez.code.data.repository.ArtWorkRepository;
import com.badAfeez.code.data.repository.OrderRepository;
import com.badAfeez.code.dtoBby.request.CreatePurchaseRequest;
import com.badAfeez.code.dtoBby.response.CreatePurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceIml implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArtWorkRepository artWorkRepository;

    @Override
    public CreatePurchaseResponse processedOrder(CreatePurchaseRequest purchaseRequest){
        CreatePurchaseResponse response = new CreatePurchaseResponse();
        ArtWorks artWorks = artWorkRepository.findById(purchaseRequest.getArtworkId()).orElseThrow(()-> new ArtWorkNotFound());
        if(purchaseRequest.getQuantity() > artWorks.getAvailableQuantity()){
            response.setMessage("Quantity requested exceeds available artworks");
            return response;
        }
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("completed");
        order.setQuantity(purchaseRequest.getQuantity());
        order.setCustomerId(purchaseRequest.getCustomerId());
        order.setArtworkId(purchaseRequest.getArtworkId());
        order.setTotalPrice(purchaseRequest.getTotalPrice());

        orderRepository.save(order);

        artWorks.setAvailableQuantity(artWorks.getAvailableQuantity() - purchaseRequest.getQuantity());
        artWorkRepository.save(artWorks);
        response.setMessage("Order purchase successful");
        return  response;
    }
}
