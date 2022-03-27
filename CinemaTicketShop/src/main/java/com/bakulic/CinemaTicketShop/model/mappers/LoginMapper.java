package com.bakulic.CinemaTicketShop.model.mappers;

import com.bakulic.CinemaTicketShop.model.User;
import com.bakulic.CinemaTicketShop.model.dto.requests.LoginUserAccountDTO;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper {

    LoginUserAccountDTO userToLoginAccountDTO (User user);
}
