package com.bakulic.CinemaTicketShop.service.validation;

import com.bakulic.CinemaTicketShop.exceptions.InvalidDataException;
import com.bakulic.CinemaTicketShop.exceptions.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {

    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");

    public void checkDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new InvalidDataException("The Date cannot be null or empty");
        }
        try {
            format.parse(date);
        }
        catch(ParseException e){
            throw new InvalidDateException("The Date provided %s is not in the right format", date);
        }
    }
}
