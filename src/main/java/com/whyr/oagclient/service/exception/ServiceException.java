package com.whyr.oagclient.service.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public ServiceException(String message) {

        this(message, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ServiceException(String message, HttpStatus httpStatus) {

        super(message);

        this.message = message;
        this.httpStatus = httpStatus;

    }

}
