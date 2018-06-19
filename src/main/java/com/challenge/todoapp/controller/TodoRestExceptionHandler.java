package com.challenge.todoapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.challenge.todoapp.exception.TodoException;

@ControllerAdvice
public class TodoRestExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler({ TodoException.class })
    public ResponseEntity<Object> handleTodoException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Error: "+ ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Unknown Error: "+ ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }    
     
}
