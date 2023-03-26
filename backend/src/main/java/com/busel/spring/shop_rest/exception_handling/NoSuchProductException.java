package com.busel.spring.shop_rest.exception_handling;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String message) {
        super(message);
    }
}
