package com.bakulic.onlineherbarium.model.mappers;

import com.bakulic.onlineherbarium.model.Role;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateRoleDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    CreateOrUpdateRoleDTO roleToCreateOrUpdateRoleDTO(Role role);

}
