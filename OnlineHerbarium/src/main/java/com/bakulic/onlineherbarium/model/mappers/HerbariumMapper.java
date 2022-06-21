package com.bakulic.onlineherbarium.model.mappers;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import org.mapstruct.Mapper;

@Mapper
public interface HerbariumMapper {

    CreateOrUpdateHerbariumDTO herbariumToCreateOrUpdateHerbariumDTO (Herbarium herbarium);

}
