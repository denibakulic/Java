package com.bakulic.CinemaTicketShop.model.mappers;

import com.bakulic.CinemaTicketShop.model.User;
import com.bakulic.CinemaTicketShop.model.dto.requests.RegisterUserAccountDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterMapper {

    RegisterUserAccountDTO userToRegisterUserAccountDTO (User user);
}
