package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class ProjectionDTO  implements Serializable {
    private int id;
    private String date;
    private String startTime;
    private  List<Ticket> ticketList;
    private Hall hall;
    private Movie movie;
    private List<Seat> seatList;

    public ProjectionDTO(Projection projection){
        if(projection != null){
            this.id = projection.getProjectionId();
            this.date = projection.getDate();
            this.startTime = projection.getStartTime();
            this.ticketList = projection.getTicketList();
            this.hall = projection.getHall();
            this.movie = projection.getMovie();
            this.seatList = projection.getSeatList();
        }
    }

}
