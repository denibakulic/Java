package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.Plant;
import lombok.Data;


import java.util.List;

@Data
public class CreateOrUpdateHerbariumDTO {

    private String title;
    private String description;
    private String picture;
    private List<Plant> plantList;
    private String date;
    private String typeName;
}
