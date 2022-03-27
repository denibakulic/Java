package com.bakulic.CinemaTicketShop.model.mappers;

import com.bakulic.CinemaTicketShop.model.User;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateUserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    CreateOrUpdateUserDTO userToCreateOrUpdateDTO (User user);
}
