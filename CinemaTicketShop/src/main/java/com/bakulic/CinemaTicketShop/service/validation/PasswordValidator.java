package com.bakulic.CinemaTicketShop.service.validation;

import com.bakulic.CinemaTicketShop.exceptions.InvalidDataException;
import com.bakulic.CinemaTicketShop.exceptions.InvalidPasswordException;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final int MAX_PASSWORD_LENGTH = 60;

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";

    private Pattern pattern;

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_REGEX);
    }

    public void checkPassword(String pswd) {
        if (pswd == null || pswd.isEmpty()) {
            throw new InvalidDataException("Password cannot be null or empty");
        }

        // check max length
        if (pswd.length() > MAX_PASSWORD_LENGTH) {
            throw new InvalidPasswordException(String.format("Password is too long: max number of chars is %s",
                    MAX_PASSWORD_LENGTH));
        }

        // Password must be at least 8 chars, 1 number, 1 upper case, 1 lower case letter, 1 special char, no spaces
        Matcher matcher = pattern.matcher(pswd);
        if (!matcher.matches()) {
            throw new InvalidPasswordException("Password must be at least 8 chars, 1 number, 1 upper case," +
                    " 1 lower case letter, 1 special char, no spaces");
        }
    }
}
