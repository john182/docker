package com.chronos.bearstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistException extends BusinessException {

    public BeerAlreadyExistException() {
        super("beers-5", HttpStatus.BAD_REQUEST);
    }

    public BeerAlreadyExistException(String code, HttpStatus status) {
        super(code, status);
    }
}
