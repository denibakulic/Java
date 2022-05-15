package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.UserPlant;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrUpdateHerbariumDTO {

    private String title;
    private String username;
    private String note;
    private List<UserPlant> plantList;
}
