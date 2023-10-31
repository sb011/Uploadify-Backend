package com.smit.Backend.Execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Not found exception class.
 * Error code: 404
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    /**
     * Constructor.
     * 
     * @param message error message
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * 
     * @param message error message
     * @param cause   error cause
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * 
     * @param cause error cause
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
