package com.bakulic.onlineherbarium.model.dto;

import com.bakulic.onlineherbarium.model.Plant;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class CreateOrUpdateUserListDTO implements Serializable {

    private String title;
    private String description;
    private String date;
    private List<Plant> plantList;
}
