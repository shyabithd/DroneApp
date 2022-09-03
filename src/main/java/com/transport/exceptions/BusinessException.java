package com.transport.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class BusinessException extends Exception {

    private static final long serialVersionUID = 2L;

    public BusinessException(String message) {
        super(message);
    }
}
