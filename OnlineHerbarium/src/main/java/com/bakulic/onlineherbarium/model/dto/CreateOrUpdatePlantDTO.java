package com.bakulic.onlineherbarium.model.dto;

import lombok.Data;

@Data
public class CreateOrUpdatePlantDTO {

    private String species;
    private String site;
    private String habitat;
    private String collected;
    private String date;
    private String familyName;
}
