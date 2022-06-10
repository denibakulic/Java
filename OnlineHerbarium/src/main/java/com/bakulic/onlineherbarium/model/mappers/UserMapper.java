package com.bakulic.onlineherbarium.model.mappers;

import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.control.MappingControl;

@Mapper
public interface UserMapper {

    CreateOrUpdateUserDTO userToCreateOrUpdateUserDTO (User user);

}
