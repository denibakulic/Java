package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.Movie;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class MovieDTO implements Serializable {
    private int id;
    private String name;
    private String description;
    private String length;
    private String picture;
    private ProjectionDTO projectionDTO;

    public MovieDTO(Movie movie){
        if(movie !=null){
            this.id = movie.getMovieId();
            this.name = movie.getName();
            this.description = movie.getDescription();
            this.length = movie.getLength();
            this.picture = movie.getPicture();
        }
    }
}

