package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.UserList;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateOrUpdatePlantDTO implements Serializable {

    private String species;
    private String site;
    private String habitat;
    private String date;
    private String name;
    private String image;
    private String description;

}
