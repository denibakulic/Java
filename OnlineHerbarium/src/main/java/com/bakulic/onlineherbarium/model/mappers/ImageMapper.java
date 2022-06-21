package com.bakulic.onlineherbarium.model.mappers;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Image;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateImageDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper {

    CreateOrUpdateImageDTO imageToCreateOrUpdateImageDTO (Image image);

}
