package com.bakulic.onlineherbarium.model.dto;

import lombok.Data;

@Data
public class CreateOrUpdatePlantDTO {


    private String family;
    private String specie;
    private String habitat;
    private String collected;
    private String date;
    private String picture;

}
