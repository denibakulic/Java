package com.bakulic.onlineherbarium.model.mappers;


import com.bakulic.onlineherbarium.model.UserList;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserListDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserListMapper {

    CreateOrUpdateUserListDTO userListToCreateOrUpdateUserListDTO (UserList userList);

}
