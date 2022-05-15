package com.bakulic.onlineherbarium.model.dto;

import lombok.Data;

@Data
public class CreateOrUpdateUserListDTO {

    private String title;
    private String username;
    private String specie;
    private String note;

}
