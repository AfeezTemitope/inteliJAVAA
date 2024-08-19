package com.badAfeez.code.services.customer;

import com.badAfeez.code.Exception.CustomerNotFound;
import com.badAfeez.code.Exception.InvalidPassword;
import com.badAfeez.code.data.models.Customer;
import com.badAfeez.code.data.models.UserRole;
import com.badAfeez.code.data.repository.CustomerRepository;
import com.badAfeez.code.dtoBby.request.LoginRequest;
import com.badAfeez.code.dtoBby.request.RegisterCustomerRequest;
import com.badAfeez.code.dtoBby.response.LoginResponse;
import com.badAfeez.code.dtoBby.response.RegisterCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceIml implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterCustomerResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        Customer customer = new Customer();
        customer.setCustomerEmail(registerCustomerRequest.getCustomerEmail());
        customer.setCustomerName(registerCustomerRequest.getCustomerName());
        customer.setCustomerAddress(registerCustomerRequest.getCustomerAddress());
        customer.setCustomerPhone(registerCustomerRequest.getCustomerPhone());
        customer.setPassword(passwordEncoder.encode(registerCustomerRequest.getPassword()));
        customer.setUserRole(registerCustomerRequest.getUserRole());
        Customer customerSaved = customerRepository.save(customer);

        RegisterCustomerResponse registerCustomerResponse = new RegisterCustomerResponse();
        registerCustomerResponse.setCustomerId(customerSaved.getCustomerId());
        registerCustomerResponse.setMessage("Customer successfully registered!");
        return registerCustomerResponse;
    }

    @Override
    public LoginResponse loginCustomer(LoginRequest loginRequest) {
        Customer customer = customerRepository.findByCustomerEmail(loginRequest.getCustomerEmail()).orElseThrow(() -> new CustomerNotFound());
        if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
            throw new InvalidPassword();
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setCustomerId(customer.getCustomerId());
        loginResponse.setMessage("Customer successfully logged in!");
        loginResponse.setRole(customer.getUserRole());
        return loginResponse;
    }


}
