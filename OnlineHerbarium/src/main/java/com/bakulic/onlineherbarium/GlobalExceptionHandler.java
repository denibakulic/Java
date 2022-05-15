package com.bakulic.onlineherbarium;

import com.bakulic.onlineherbarium.exceptions.ErrorDetails;
import com.bakulic.onlineherbarium.exceptions.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.relation.RoleNotFoundException;

/** Handles the exceptions globally */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidDataException.class,
            InvalidIdentifierException.class, InvalidUsernameException.class,
            InvalidLoginException.class, InvalidPasswordException.class, InvalidEmailException.class,
            ObjectNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleAsBadRequest(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RoleNotFoundException.class, ObjectNotFoundException.class,})
    public ResponseEntity<ErrorDetails> handleAsNotFound(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}