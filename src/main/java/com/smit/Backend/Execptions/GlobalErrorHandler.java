package com.smit.Backend.Execptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.smit.Backend.Models.ErrorResponse;

/**
 * Global error handler class.
 */
@ControllerAdvice
public class GlobalErrorHandler {

    /**
     * This method handles not found exceptions.
     * 
     * @param exception not found exception
     * @return error response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles unauthorized exceptions.
     * 
     * @param exception unauthorized exception
     * @return error response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UnauthorizedException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * This method handles bad request exceptions.
     * 
     * @param exception bad request exception
     * @return error response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles all other exceptions.
     * 
     * @param exception exception
     * @return error response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
