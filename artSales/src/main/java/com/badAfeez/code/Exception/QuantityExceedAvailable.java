package com.badAfeez.code.Exception;

public class QuantityExceedAvailable extends Exception {
    public QuantityExceedAvailable() {
        super("Quantity Exceeds Available artworks");
    }
}
