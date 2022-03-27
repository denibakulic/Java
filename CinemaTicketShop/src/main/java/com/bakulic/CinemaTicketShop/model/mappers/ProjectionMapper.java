package com.bakulic.CinemaTicketShop.model.mappers;

import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateProjectionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectionMapper {

    CreateOrUpdateProjectionDTO projectionToCreateOrUpdateProjectionDTO (Projection projection);
}
