package com.bakulic.CinemaTicketShop.model.mappers;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateHallDTO;
import org.mapstruct.Mapper;

@Mapper
public interface HallMapper {

    CreateOrUpdateHallDTO hallToCreateOrUpdateDTO (Hall hall);
}
