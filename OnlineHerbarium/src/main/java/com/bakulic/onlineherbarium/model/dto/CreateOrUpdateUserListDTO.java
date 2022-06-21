package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.Plant;
import lombok.Data;


import java.util.List;

@Data
public class CreateOrUpdateUserListDTO {

    private String title;
    private String description;
    private String date;
    private List<Plant> plantList;
    private int userId;
}
