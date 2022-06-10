package com.bakulic.onlineherbarium.model.mappers;

import com.bakulic.onlineherbarium.model.Family;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateFamilyDTO;
import org.mapstruct.Mapper;

@Mapper
public interface FamilyMapper {

    CreateOrUpdateFamilyDTO FamilyToCreateOrUpdateDTO (Family family);

}
