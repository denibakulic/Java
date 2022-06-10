package com.bakulic.onlineherbarium.model.mappers;

import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdatePlantDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PlantMapper {

    CreateOrUpdatePlantDTO plantToCreateOrUpdatePlantDTO(Plant plant);

}
