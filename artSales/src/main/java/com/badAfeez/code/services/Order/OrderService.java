package com.badAfeez.code.services.Order;

import com.badAfeez.code.dtoBby.request.CreatePurchaseRequest;
import com.badAfeez.code.dtoBby.response.CreatePurchaseResponse;


public interface OrderService {
    CreatePurchaseResponse processedOrder(CreatePurchaseRequest purchaseRequest);

}
