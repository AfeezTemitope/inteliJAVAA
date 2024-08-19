package com.badAfeez.code.controller;

import com.badAfeez.code.dtoBby.request.LoginRequest;
import com.badAfeez.code.dtoBby.request.RegisterCustomerRequest;
import com.badAfeez.code.dtoBby.response.LoginResponse;
import com.badAfeez.code.dtoBby.response.RegisterCustomerResponse;
import com.badAfeez.code.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterCustomerRequest registerCustomerRequest){
        try {
            RegisterCustomerResponse registerCustomerResponse = customerService.registerCustomer(registerCustomerRequest);
            return new ResponseEntity<>(registerCustomerResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest){
        try {
            LoginResponse loginResponse = customerService.loginCustomer(loginRequest);
            return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
