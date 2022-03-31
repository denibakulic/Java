package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.Projection;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class HallDTO implements Serializable {

    private int id;
    private String name;
    private Integer numberOfSeats;
    private String description;
    private List<Projection> projectionList;

    public HallDTO (Hall hall){
        if(hall != null){
            this.id = hall.getHallId();
            this.name = hall.getName();
            this.numberOfSeats = hall.getNumberOfSeats();
            this.description = hall.getDescription();

        }
    }
}
