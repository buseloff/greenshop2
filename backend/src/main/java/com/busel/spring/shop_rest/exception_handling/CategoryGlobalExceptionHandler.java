package com.busel.spring.shop_rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CategoryIncorrectData> handleException(NoSuchCategoryException exception) {
        CategoryIncorrectData data = new CategoryIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CategoryIncorrectData> handleException(Exception exception) {
        CategoryIncorrectData data = new CategoryIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
