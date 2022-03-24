package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class ProjectionDTO  implements Serializable {
    private int id;
    private String date;
    private String startTime;
    private List<Seat> seatList;
    private  List<Ticket> ticketList;
    private Hall hall;
    private Movie movie;

    public ProjectionDTO(Projection projection){
        if(projection != null){
            this.id = projection.getProjectionId();
            this.date = projection.getDate();
            this.startTime = projection.getStartTime();
            this.seatList = projection.getSeatList();
            this.ticketList = projection.getTicketList();
            this.hall = projection.getHall();
            this.movie = projection.getMovie();
        }
    }

}
