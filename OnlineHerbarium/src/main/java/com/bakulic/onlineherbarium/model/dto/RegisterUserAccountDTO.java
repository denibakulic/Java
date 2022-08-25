package com.bakulic.onlineherbarium.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterUserAccountDTO implements Serializable {


    private String password;
    private String fullname;
    private String username;
    private Boolean role;

}

