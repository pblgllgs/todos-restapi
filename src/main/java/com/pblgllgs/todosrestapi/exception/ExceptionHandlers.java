package com.pblgllgs.todosrestapi.exception;
/*
 *
 * @author pblgl
 * Created on 15-05-2025
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponses> handleResponseStatusException(ResponseStatusException exc) {
        return buildResponseEntity(exc,HttpStatus.valueOf(exc.getStatusCode().value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponses> handleResponseStatusException(Exception exc) {
        return buildResponseEntity(exc,HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponses> buildResponseEntity(Exception exc, HttpStatus status) {
        ExceptionResponses errorResponses = new ExceptionResponses();
        errorResponses.setStatus(status.value());
        errorResponses.setMessage(exc.getMessage());
        errorResponses.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponses, status);
    }
}
