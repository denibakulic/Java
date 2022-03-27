package com.bakulic.CinemaTicketShop.model.mappers;

import com.bakulic.CinemaTicketShop.model.Movie;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateMovieDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

    CreateOrUpdateMovieDTO movieToCreateOrUpdateMovieDTO (Movie movie);
}
