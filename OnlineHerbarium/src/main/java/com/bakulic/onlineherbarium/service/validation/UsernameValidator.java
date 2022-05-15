package com.bakulic.onlineherbarium.service.validation;

import com.bakulic.onlineherbarium.exceptions.InvalidDataException;
import com.bakulic.onlineherbarium.exceptions.InvalidUsernameException;

public class UsernameValidator {

    private static final int MAX_USERNAME_LENGTH = 20;

    public void checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new InvalidDataException("The Username cannot be null or empty");
        }

        // check max email length
        if (username.length() > MAX_USERNAME_LENGTH) {
            throw new InvalidUsernameException(String.format("The Username is too long: max number of chars is %s",
                    MAX_USERNAME_LENGTH));
        }
    }
}

