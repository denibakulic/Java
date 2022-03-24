package com.bakulic.CinemaTicketShop.exceptions;

public class InvalidTimeException extends RuntimeException{

    public InvalidTimeException(String message) {
        super(message);
    }
}
