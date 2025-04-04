package com.ks.app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserTokenException extends ResponseStatusException {

    public UserTokenException(HttpStatus status) {
        super(status);
    }
}
