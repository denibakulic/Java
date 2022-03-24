package com.bakulic.CinemaTicketShop.exceptions;

public class InvalidAccessException extends RuntimeException{
    public InvalidAccessException(String message) {
        super(message);
    }
}
