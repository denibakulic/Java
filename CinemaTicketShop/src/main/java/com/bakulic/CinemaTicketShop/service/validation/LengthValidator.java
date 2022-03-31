package com.bakulic.CinemaTicketShop.service.validation;

import com.bakulic.CinemaTicketShop.exceptions.InvalidDataException;
import com.bakulic.CinemaTicketShop.exceptions.InvalidLengthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LengthValidator {
    private static final String LENGTH_REGEX = "/^[0-300]min$/";

    private Pattern pattern;

    public LengthValidator(){pattern = Pattern.compile(LENGTH_REGEX);}

    public void  checkLength(String length){
        if (length == null || length.isEmpty()) {
            throw new InvalidDataException("The length cannot be null or empty");
        }
        Matcher matcher = pattern.matcher(length);
        if (!matcher.matches()) {
            throw new InvalidLengthException(String.format("The length provided %s is not in the right format", length));
        }
    }
}
