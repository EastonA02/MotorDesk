package com.Easton.motordesk_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception: thrown when a requested employee doesn't exist
// Http.NotFound: when exception is thrown, respond with this..
@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {

        //pass error message
        super(message);
    }
}
