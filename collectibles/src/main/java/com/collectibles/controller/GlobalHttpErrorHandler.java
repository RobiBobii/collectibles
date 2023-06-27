package com.collectibles.controller;

import com.collectibles.exceptions.BookNotFoundException;
import com.collectibles.exceptions.CollectionNotFoundException;
import com.collectibles.exceptions.QuoteNotFoundException;
import com.collectibles.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception) {
        return new ResponseEntity<>("Book with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CollectionNotFoundException.class)
    public ResponseEntity<Object> handleCollectionNotFoundException(CollectionNotFoundException exception) {
        return new ResponseEntity<>("Collection with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<Object> handleQuoteNotFoundException(QuoteNotFoundException exception) {
        return new ResponseEntity<>("Quote with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
    }
}