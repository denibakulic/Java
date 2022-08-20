package com.bakulic.onlineherbarium.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserAccountDTO implements Serializable {

    private String email;
    private String password;

}
