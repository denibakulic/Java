package com.bakulic.onlineherbarium.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterUserAccountDTO implements Serializable {


    private String username;
    private String password;
    private String fullname;
    private String email;
    private String role;


}

