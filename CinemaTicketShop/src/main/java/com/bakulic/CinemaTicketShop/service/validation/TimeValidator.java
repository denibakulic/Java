package com.bakulic.CinemaTicketShop.service.validation;

import com.bakulic.CinemaTicketShop.exceptions.InvalidDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeValidator {

    private static final String TIME_REGEX = "/^˙([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/";

    private Pattern pattern;

    public TimeValidator(){pattern = Pattern.compile(TIME_REGEX);}

    public void  checkTime(String time){
        if (time == null || time.isEmpty()) {
            throw new InvalidDataException("The Time cannot be null or empty");
        }
        Matcher matcher = pattern.matcher(time);
        if (!matcher.matches()) {
            throw new InvalidDataException(String.format("The Time provided %s is not in the right format", time));
        }
    }
}
