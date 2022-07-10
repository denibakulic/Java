package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.UserList;
import lombok.Data;

@Data
public class CreateOrUpdatePlantDTO {

    private String species;
    private String site;
    private String habitat;
    private String date;
    private String familyName;
    private String image;
    private String description;
    private String plantLink;
}
