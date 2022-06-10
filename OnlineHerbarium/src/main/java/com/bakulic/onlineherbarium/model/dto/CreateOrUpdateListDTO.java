package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.Plant;
import lombok.Data;

import java.util.List;
@Data
public class CreateOrUpdateListDTO {

    private String title;
    private String description;
    private List<Plant> plantList; //vjv trebat izmjene
    private String date;
    private String typeName;
}
