package com.bakulic.onlineherbarium.model.dto;

import lombok.Data;

@Data
public class CreateOrUpdateUserDTO {

    private String username;
    private String password;
    private String fullname;
    private String email;
    private int roleId;
}
