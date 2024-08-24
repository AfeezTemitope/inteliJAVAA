package com.badAfeez.code.services.customer;

import com.badAfeez.code.dtoBby.request.LoginRequest;
import com.badAfeez.code.dtoBby.request.RegisterCustomerRequest;
import com.badAfeez.code.dtoBby.response.LoginResponse;
import com.badAfeez.code.dtoBby.response.RegisterCustomerResponse;
import org.springframework.stereotype.Service;


public interface CustomerService {
    RegisterCustomerResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest);
    LoginResponse loginCustomer(LoginRequest loginRequest);

}
