package com.smit.Backend.Execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Bad request exception class.
 * Error code: 400
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
     * Constructor.
     * 
     * @param message error message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * 
     * @param message error message
     * @param cause   error cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * 
     * @param cause error cause
     */
    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
