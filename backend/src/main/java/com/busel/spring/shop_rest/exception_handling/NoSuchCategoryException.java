package com.busel.spring.shop_rest.exception_handling;

public class NoSuchCategoryException extends RuntimeException {
    public NoSuchCategoryException(String message) {
        super(message);
    }
}
