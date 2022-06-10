package com.bakulic.onlineherbarium.model.mappers;

import com.bakulic.onlineherbarium.model.HerbariumOrList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import org.mapstruct.Mapper;

@Mapper
public interface HerbariumListMapper {

    CreateOrUpdateHerbariumDTO herbariumListToCreateOrUpdateHerbariumListDTO (HerbariumOrList herbariumOrList);

}
