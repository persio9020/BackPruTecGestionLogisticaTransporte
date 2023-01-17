package com.transporte.logistica.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author persi
 */
public class BusinessRuleException extends RuntimeException  {
   
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    
    public BusinessRuleException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    } 
}

