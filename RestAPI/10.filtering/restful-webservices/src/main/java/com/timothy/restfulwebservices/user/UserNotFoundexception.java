package com.timothy.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class UserNotFoundexception extends RuntimeException {
    public UserNotFoundexception(String s) {
        super(s);
    }
}
