package com.badAfeez.code.Exception;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound(){
        super("Customer not found");

    }
}
