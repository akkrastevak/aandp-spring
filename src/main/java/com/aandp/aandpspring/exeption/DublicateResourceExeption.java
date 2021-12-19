package com.aandp.aandpspring.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DublicateResourceExeption extends RuntimeException{

    public DublicateResourceExeption(String message) {
        super(message);
    }
}
