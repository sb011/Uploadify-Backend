package com.smit.Backend.Execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Unauthorized exception class.
 * Error code: 401
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    /**
     * Constructor.
     * 
     * @param message error message
     */
    public UnauthorizedException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * 
     * @param message error message
     * @param cause   error cause
     */
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * 
     * @param cause error cause
     */
    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
